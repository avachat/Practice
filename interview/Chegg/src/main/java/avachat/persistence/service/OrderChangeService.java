package avachat.persistence.service;

import avachat.failure.Params;
import avachat.persistence.entity.OrderAddress;
import avachat.persistence.entity.StatusTracker;
import avachat.persistence.entity.UserAddress;
import avachat.persistence.repository.OrderAddressRepository;
import avachat.persistence.repository.StatusTrackerRepository;
import avachat.persistence.repository.UserAddressRepository;
import avachat.warehouse.WarehouseClient;
import avachat.webapp.api.input.OrderChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderChangeService {


    // TODO : Make these enums
    public static final String STATUS_TRACKER_INIT = "init";
    public static final String STATUS_TRACKER_COMPLETE = "complete";
    public static final String STATUS_TRACKER_ROLL_BACK = "rollback";

    public static class OrderChangeTracker {
        long statusTrackerId;
        OrderAddress prevOrderAddress;
        OrderAddress newOrderAddress;

        public OrderChangeTracker(long statusTrackerId,
                                  OrderAddress prevOrderAddress,
                                  OrderAddress newOrderAddress) {
            this.statusTrackerId = statusTrackerId;
            this.prevOrderAddress = prevOrderAddress;
            this.newOrderAddress = newOrderAddress;
        }
    }

    private final OrderService orderService;
    private final UserAddressRepository userAddressRepository;
    private final OrderAddressRepository orderAddressRepository;
    private final StatusTrackerRepository statusTrackerRepository;

    public OrderChangeService(@Autowired OrderService orderService,
                              @Autowired UserAddressRepository userAddressRepository,
                              @Autowired OrderAddressRepository orderAddressRepository,
                              @Autowired StatusTrackerRepository statusTrackerRepository) {
        this.orderService = orderService;
        this.userAddressRepository = userAddressRepository;
        this.orderAddressRepository = orderAddressRepository;
        this.statusTrackerRepository = statusTrackerRepository;
    }


    private OrderAddress retrieveExistingAddress (long orderId) {

        // Get all the addresses associated with this order
        // Under normal circumstances there would be only one address
        // But if another address change request is going on, we may get more that one
        List<OrderAddress> orderAddressList = orderService.findByOrderId(orderId);

        if ( (orderAddressList == null) || (orderAddressList.size() == 0)) {
            throw new IllegalArgumentException("No existing address found with orderId = " + orderId);
        }

        if (orderAddressList.size() > 1) {
            throw new IllegalArgumentException("For orderID = " + orderId
                    + " there are already " + orderAddressList.size() + " addresses");
        }

        // Now we know that there is only one address
        return orderAddressList.get(0);
    }

    /**
     * In one transaction :
     *  1. Create a new address
     *  2. Create a new order address
     *  3. Create a new status tracker
     *
     * @param orderId
     * @param newAddress
     * @return
     */
    @Transactional
    public OrderChangeTracker createNewAddressForAnOrderAndCommit(long remoteStatusId,
                                                                  long orderId,
                                                                  UserAddress newAddress) {

        // retrieve existing address for this order
        OrderAddress existingOrderAddress = retrieveExistingAddress(orderId);
        log.debug("Retrieved address : " + existingOrderAddress);

        // update the user object for the new address
        newAddress.setUser(existingOrderAddress.getUserAddress().getUser());

        // create a new order address object
        OrderAddress newOrderAddress = new OrderAddress(newAddress, existingOrderAddress.getOrder());


        // save the new order address
        log.info("Saving the new address to DB " + newAddress);
        userAddressRepository.save(newAddress);
        log.info("Saving new order address to DB " + newOrderAddress);
        OrderAddress createdOrderAddress = orderAddressRepository.save(newOrderAddress);

        // Create a tracking record
        StatusTracker statusTracker =
                new StatusTracker(STATUS_TRACKER_INIT,
                        remoteStatusId,
                        orderId,
                        existingOrderAddress.getOrderAddressId(),
                        newOrderAddress.getOrderAddressId());

        // save it
        statusTrackerRepository.save(statusTracker);
        long statusTrackerId = statusTracker.getStatusTrackerId();

        return new OrderChangeTracker(statusTrackerId, existingOrderAddress, createdOrderAddress);
    }


    /**
     * In one transaction
     *  1. Find the ID of the older address
     *  2. Delete that older record
     *  3. Mark the transaction status as complete
     * @param orderChangeTracker
     */
    @Transactional
    public void completeChangeOfAddressAndCommit(OrderChangeTracker orderChangeTracker) {

        // Read from change tracker table
        StatusTracker statusTracker =
                statusTrackerRepository.findById(orderChangeTracker.statusTrackerId)
                .orElseThrow(() -> new IllegalArgumentException
                        ("Weird : could not find status tracker with id = "
                                + orderChangeTracker.statusTrackerId));

        // Delete the older address now
        deleteOrderAddress(orderChangeTracker.prevOrderAddress);

        // Update the change tracker table
        statusTracker.setStatus(STATUS_TRACKER_COMPLETE);
        statusTracker.setEndedAtToNow();
        statusTrackerRepository.save(statusTracker);
    }

    /**
     * Deletes address for an order
     *
     * @param orderAddress
     */
    @Transactional
    public void deleteOrderAddress (OrderAddress orderAddress) {
        log.debug("Deleting order address from " + orderAddress);
        orderAddressRepository.delete(orderAddress);
    }


    @Transactional
    // public only for marking transactional
    public void restorePreviousOrderAddress (OrderChangeTracker orderChangeTracker) {


        // Read from change tracker table
        StatusTracker statusTracker =
                statusTrackerRepository.findById(orderChangeTracker.statusTrackerId)
                        .orElseThrow(() -> new IllegalArgumentException
                                ("Weird : could not find status tracker with id = "
                                        + orderChangeTracker.statusTrackerId));

        OrderAddress previousOrderAddress = orderChangeTracker.prevOrderAddress;
        OrderAddress createdOrderAddress = orderChangeTracker.newOrderAddress;

        log.info("Deleting newly created order address " + createdOrderAddress);
        deleteOrderAddress(createdOrderAddress);

        log.info("Restoring prev order address " + previousOrderAddress);
        orderAddressRepository.save(previousOrderAddress);

        // Update the change tracker table
        statusTracker.setStatus(STATUS_TRACKER_ROLL_BACK);
        statusTracker.setEndedAtToNow();
        statusTrackerRepository.save(statusTracker);

    }

}
