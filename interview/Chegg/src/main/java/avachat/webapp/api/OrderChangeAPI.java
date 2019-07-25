package avachat.webapp.api;

import avachat.persistence.entity.OrderAddress;
import avachat.persistence.service.OrderChangeService;
import avachat.webapp.api.input.OrderChange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderChangeAPI {

    private final OrderChangeService orderChangeService;


    public OrderChangeAPI(@Autowired OrderChangeService orderChangeService) {
        this.orderChangeService = orderChangeService;
    }


    @PutMapping("/naive/orders/{id}/addresses")
    public OrderAddress changeAddressOfAnExistingOrder(@PathVariable long id,
                                                       @RequestBody OrderChange orderChange) {
        return orderChangeService.changeAddressOfAnOrderNaive
                (id, orderChange.getAddress(), orderChange.getFailures());
    }
}
