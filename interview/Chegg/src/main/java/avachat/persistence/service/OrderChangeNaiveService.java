package avachat.persistence.service;

import avachat.failure.Params;
import avachat.persistence.entity.OrderAddress;
import avachat.persistence.entity.UserAddress;
import avachat.warehouse.WarehouseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class OrderChangeNaiveService {

    private final OrderChangeService orderChangeService;
    private final WarehouseClient warehouseClient;

    public OrderChangeNaiveService(@Autowired OrderChangeService orderChangeService,
                                   @Autowired WarehouseClient warehouseClient) {
        this.orderChangeService = orderChangeService;
        this.warehouseClient = warehouseClient;
    }


    /**
     * A very naive implementation of an attempted distributed transaction.
     *
     * NOTE : This is for the purpose of discussion only
     *
     * @param orderId
     * @param newAddress
     * @param failureInjection
     * @return
     */
    public OrderAddress changeAddressOfAnOrderNaive (long orderId,
                                                     UserAddress newAddress,
                                                     Map<String, Params> failureInjection) {

        // commit locally
        log.info("NAIVE : Adding address change for " + orderId);
        long remoteStatusId = -1;  // NOTE: NOT tracking any remote transaction
        // NOTE : Starts and commits a transaction
        OrderChangeService.OrderChangeTracker orderChangeTracker
                = orderChangeService.createNewAddressForAnOrderAndCommit(remoteStatusId, orderId, newAddress);
        OrderAddress createdOrderAddress = orderChangeTracker.newOrderAddress;

        // call the remote server
        boolean success = warehouseClient.callRemoteServerNaive(orderId, failureInjection);

        // FIXME : There may be a timeout here!!!
        // NOTE : In case of timeout, there is NO way of knowing if the remote server succeeded or not

        if (success) {
            log.info("NAIVE : Successful address change for " + orderId);
            return createdOrderAddress;
        }

        // Something failed on remote server
        // try to undo
        // NOTE : In case of say power failure at this point, we won't be able to undo
        // NOTE : And if that happens, the system will be in inconsistent state
        // NOTE : The bigger problems is : we may never know that the system state is not consistent
        log.info("NAIVE : Undoing address change for " + orderId);
        // NOTE : Starts and commits a transaction
        orderChangeService.restorePreviousOrderAddress(orderChangeTracker);

        // return error
        throw new RuntimeException("NAIVE : Could not change address, as remote server failed");
    }
}
