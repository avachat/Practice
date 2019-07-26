package avachat.webapp.api;

import avachat.persistence.entity.OrderAddress;
import avachat.persistence.service.OrderChangeDistributedService;
import avachat.persistence.service.OrderChangeNaiveService;
import avachat.persistence.service.OrderChangeService;
import avachat.webapp.api.input.OrderChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class OrderChangeAPI {

    private final OrderChangeNaiveService orderChangeNaiveService;
    private final OrderChangeDistributedService orderChangeDistributedService;

    public OrderChangeAPI(@Autowired OrderChangeNaiveService orderChangeNaiveService,
                          @Autowired OrderChangeDistributedService orderChangeDistributedService) {
        this.orderChangeNaiveService = orderChangeNaiveService;
        this.orderChangeDistributedService = orderChangeDistributedService;
    }


    @PutMapping("/naive/orders/{id}/addresses")
    public OrderAddress changeAddressOfAnExistingOrderNaive(@PathVariable long id,
                                                       @RequestBody OrderChange orderChange) {
        return orderChangeNaiveService.changeAddressOfAnOrderNaive
                (id, orderChange.getAddress(), orderChange.getFailures());
    }


    @PutMapping("/smart/orders/{id}/addresses")
    public OrderAddress changeAddressOfAnExistingOrderSmart(@PathVariable long id,
                                                       @RequestBody OrderChange orderChange) {
        Optional<OrderAddress> optionalOrderAddress=
                orderChangeDistributedService.changeAddressOfAnOrderSmart
                        (id, orderChange.getAddress(), orderChange.getFailures());

        if (! optionalOrderAddress.isPresent()) {
            // NOTE : This exception is for demonstration purpose only
            throw new RuntimeException
                    ("Your request is still being processed. Please check back later with order id " + id);
        }

        return optionalOrderAddress.get();
    }

}
