package avachat.persistence.service;

import avachat.persistence.entity.Order;
import avachat.persistence.entity.OrderAddress;
import avachat.persistence.entity.UserAddress;
import avachat.persistence.repository.OrderAddressRepository;
import avachat.persistence.repository.OrderRepository;
import avachat.persistence.repository.UserAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderAddressRepository orderAddressRepository;
    private final UserAddressRepository userAddressRepository;


    public OrderService(@Autowired OrderRepository orderRepository,
                        @Autowired OrderAddressRepository orderAddressRepository,
                        @Autowired UserAddressRepository userAddressRepository) {
        this.orderRepository = orderRepository;
        this.orderAddressRepository = orderAddressRepository;
        this.userAddressRepository = userAddressRepository;
    }

    public OrderAddress createOneOrderAtAddress(long addressId, Order order) {

        UserAddress userAddress =
                userAddressRepository.findById(addressId)
                        .orElseThrow(() -> new IllegalArgumentException("Unknown addressID " + addressId));

        // first create the order
        Order createdOrder = orderRepository.save(order);
        log.debug("Created order " + createdOrder);

        // now create the order address
        OrderAddress orderAddress = new OrderAddress(userAddress, createdOrder);
        log.debug("Saving orderAddress " + orderAddress);
        return orderAddressRepository.save(orderAddress);
    }

    public Optional<Order> findById(long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<OrderAddress> findByOrderId(long orderId) {
        return orderAddressRepository.findAllByOrderOrderId(orderId);
    }

    public List<OrderAddress> findByAddressId(long addressId) {
        return orderAddressRepository.findAllByUserAddressAddressId(addressId);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
