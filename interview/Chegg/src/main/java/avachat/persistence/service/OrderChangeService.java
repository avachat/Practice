package avachat.persistence.service;

import avachat.failure.Params;
import avachat.persistence.entity.OrderAddress;
import avachat.persistence.entity.UserAddress;
import avachat.persistence.repository.OrderAddressRepository;
import avachat.persistence.repository.UserAddressRepository;
import avachat.warehouse.WarehouseClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class OrderChangeService {


    private static class PrevAndNew {
        OrderAddress prevOrderAddress;
        OrderAddress newOrderAddress;

        public PrevAndNew(OrderAddress prevOrderAddress, OrderAddress newOrderAddress) {
            this.prevOrderAddress = prevOrderAddress;
            this.newOrderAddress = newOrderAddress;
        }
    }

    private final OrderService orderService;
    private final UserAddressRepository userAddressRepository;
    private final OrderAddressRepository orderAddressRepository;
    private final WarehouseClient warehouseClient;

    public OrderChangeService(@Autowired OrderService orderService,
                              @Autowired UserAddressRepository userAddressRepository,
                              @Autowired OrderAddressRepository orderAddressRepository,
                              @Autowired WarehouseClient warehouseClient) {
        this.orderService = orderService;
        this.userAddressRepository = userAddressRepository;
        this.orderAddressRepository = orderAddressRepository;
        this.warehouseClient = warehouseClient;
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

    @Transactional
    // public only for marking transactional
    public PrevAndNew changeAddressOfAnOrderAndCommit(long orderId,
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

        return new PrevAndNew(existingOrderAddress, createdOrderAddress);
    }


    @Transactional
    // public only for marking transactional
    public void deleteOrderAddress (OrderAddress orderAddress) {
        log.debug("Deleting order address from " + orderAddress);
        orderAddressRepository.delete(orderAddress);
    }


    @Transactional
    // public only for marking transactional
    public void restorePreviousOrderAddress (PrevAndNew prevAndNew) {

        OrderAddress previousOrderAddress = prevAndNew.prevOrderAddress;
        OrderAddress createdOrderAddress = prevAndNew.newOrderAddress;

        log.info("Deleting newly created order address " + createdOrderAddress);
        deleteOrderAddress(createdOrderAddress);

        log.info("Restoring prev order address " + previousOrderAddress);
        orderAddressRepository.save(previousOrderAddress);

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
        log.info("Adding address change for " + orderId);
        PrevAndNew prevAndNew = changeAddressOfAnOrderAndCommit(orderId, newAddress);
        OrderAddress createdOrderAddress = prevAndNew.newOrderAddress;

        // call the remote server
        boolean success = warehouseClient.callRemoteServerNaive(orderId, failureInjection);

        // FIXME : There may be a timeout here!!!
        // NOTE : In case of timeout, there is NO way of knowing if the remote server succeeded or not

        if (success) {
            log.info("Successful address change for " + orderId);
            return createdOrderAddress;
        }

        // Something failed on remote server
        // try to undo
        // NOTE : In case of say power failure at this point, we won't be able to undo
        // NOTE : And if that happens, the system will be in inconsistent state
        // NOTE : The bigger problems is : we may never know that the system state is not consistent
        log.info("Undoing address change for " + orderId);
        restorePreviousOrderAddress(prevAndNew);

        // return error
        throw new RuntimeException("Could not change address, as remote server failed");
    }
}
