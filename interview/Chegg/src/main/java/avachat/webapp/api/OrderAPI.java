package avachat.webapp.api;

import avachat.persistence.entity.Order;
import avachat.persistence.entity.OrderAddress;
import avachat.persistence.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OrderAPI {

    private final OrderService orderService;


    public OrderAPI(@Autowired OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/addresses/{addressId}/orders")
    public OrderAddress createOneOrderAtAddress(@PathVariable long addressId, @RequestBody Order order) {
        return orderService.createOneOrderAtAddress(addressId, order);
    }

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order findById(@PathVariable long id) {
        return orderService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No order with id " + id));
    }

    @GetMapping("/addresses/{addressId}/orders")
    public List<OrderAddress> findAllByAddressId(@PathVariable long addressId) {
        return orderService.findByAddressId(addressId);
    }

    @GetMapping("/orders/{id}/addresses")
    public List<OrderAddress> findAllByOrderId(@PathVariable long id) {
        return orderService.findByOrderId(id);
    }

}
