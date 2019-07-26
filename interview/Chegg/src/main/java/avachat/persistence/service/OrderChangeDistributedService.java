package avachat.persistence.service;

import avachat.failure.Params;
import avachat.persistence.entity.OrderAddress;
import avachat.persistence.entity.UserAddress;
import avachat.persistence.repository.OrderAddressRepository;
import avachat.persistence.repository.UserAddressRepository;
import avachat.persistence.service.OrderChangeService.OrderChangeTracker;
import avachat.warehouse.WarehouseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class OrderChangeDistributedService {

    private static final long TIMEOUT_INTERVAL = 5L;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;

    private final OrderService orderService;
    private final OrderChangeService orderChangeService;
    private final UserAddressRepository userAddressRepository;
    private final OrderAddressRepository orderAddressRepository;
    private final WarehouseClient warehouseClient;


    public OrderChangeDistributedService(@Autowired OrderService orderService,
                                         @Autowired OrderChangeService orderChangeService,
                                         @Autowired UserAddressRepository userAddressRepository,
                                         @Autowired OrderAddressRepository orderAddressRepository,
                                         @Autowired WarehouseClient warehouseClient) {
        this.orderService = orderService;
        this.orderChangeService = orderChangeService;
        this.userAddressRepository = userAddressRepository;
        this.orderAddressRepository = orderAddressRepository;
        this.warehouseClient = warehouseClient;
    }


    /**
     * Changes address of an order on local and remote server.
     *
     * NOTE : The strategy here is eventual consistency.
     * NOTE : This is one of the possible strategies for implementing a distributed transaction.
     * NOTE : The status of this operation is SUCCESS, FAILURE and a MAYBE.
     * NOTE : In case of MAYBE, the client will have to check back.
     *
     * @param orderId
     * @param newAddress
     * @param failureInjection
     * @return Optional order address
     * NOTE : SUCCESS indicated by a filled Optional
     * NOTE : FAILURE indicated by an exception
     * NOTE : MAYBE indicated by an empty optional
     */
    public Optional<OrderAddress> changeAddressOfAnOrderSmart (long orderId,
                                                               UserAddress newAddress,
                                                               Map<String, Params> failureInjection) {


        // TODO : Check if another address change operation is in flight for this order
        // TODO : It can be easily checked by reading local status table

        // --------------------------------------------------------------------------------------
        // perform handshake and init the transaction with the remote server
        //
        Future<Long> futureRemoteStatusId =
                warehouseClient.initProcessingOnRemoteServerSmart(orderId, failureInjection);

        // Get the result of INIT
        long remoteStatusId = -1;
        try {
            remoteStatusId = futureRemoteStatusId.get(TIMEOUT_INTERVAL, TIME_UNIT);
        } catch (Exception e) {
            // INIT failed : Nothing more to do : inform client via an exception
            // TODO : Maybe here pre-determined retries can be added with exponential backoff
            log.error("TIMEOUT while INIT for " + orderId);
            throw new RuntimeException(e);
        }

        // --------------------------------------------------------------------------------------
        // Init succeeded : create a LOCAL tracking record, and a new address
        //
        OrderChangeTracker orderChangeTracker
                = orderChangeService.createNewAddressForAnOrderAndCommit(remoteStatusId, orderId, newAddress);



        // --------------------------------------------------------------------------------------
        // Send request to remote server : with the ID obtained via handshake
        //
        Future<Boolean> futureRemoteSuccess =
                warehouseClient.processOnRemoteServerSmart(remoteStatusId, failureInjection);
        boolean remoteSuccess;
        try {
            remoteSuccess = futureRemoteSuccess.get(TIMEOUT_INTERVAL, TIME_UNIT);
        } catch (Exception e) {
            // NOTE : GOT A TIMEOUT !!!
            // NOTE : Now there is NO way of knowing at this point, if it succeeded remotely
            // NOTE : Maybe a query to remote server can be made to get the status
            // NOTE : For this implementation, that check is performed in a scheduled thread
            // NOTE : In any case, the client will have to check back to see the status
            log.warn("TIMEOUT while PROCESS for " + orderId);
            log.warn("Client will have to check back");
            return Optional.empty();
        }

        // --------------------------------------------------------------------------------------
        // Depending on the status of the remote server
        // Either complete the transaction locally, or undo it
        // NOTE : There can be failures at this point - say a power failure
        // NOTE : In that case, the transaction is still incomplete on the local side
        // NOTE : The status is committed durably on the remote server
        // NOTE : The scheduled reconciler thread can check if there is any incomplete transaction
        // NOTE : The tracking table record has enough information to complete or rollback the transaction

        if (! remoteSuccess) {
            // NOTE : Undo the changes locally and commit
            orderChangeService.restorePreviousOrderAddress(orderChangeTracker);
            throw new RuntimeException("SMART : Could not change address, as remote server failed");
        }

        // Complete the transaction locally
        orderChangeService.completeChangeOfAddressAndCommit(orderChangeTracker);
        return Optional.of(orderChangeTracker.newOrderAddress);

    }
}
