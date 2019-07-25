package avachat.webapp.api;

import avachat.persistence.entity.UserAddress;
import avachat.persistence.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserAddressAPI {

    private final UserAddressService userAddressService;

    public UserAddressAPI(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @PostMapping("/users/{userId}/addresses")
    public UserAddress createOne(@PathVariable long userId,  @RequestBody UserAddress userAddress) {
        return userAddressService.createOne(userId, userAddress);
    }

    @GetMapping("/addresses")
    public List<UserAddress> findAll() {
        return userAddressService.findAll();
    }
}
