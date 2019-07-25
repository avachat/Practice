package avachat.webapp.api;

import avachat.persistence.entity.UserAddress;
import avachat.persistence.service.UserAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/users/{userId}/addresses")
    public List<UserAddress> findByUserId(@PathVariable long userId) {
        return userAddressService.findByUserId(userId);
    }

    @GetMapping("/addresses")
    public List<UserAddress> findAll() {
        return userAddressService.findAll();
    }

    @GetMapping("/addresses/{id}")
    public UserAddress findOne(@PathVariable long id) {
        Optional<UserAddress> optionalUserAddress = userAddressService.findById(id);
        if (! optionalUserAddress.isPresent()) {
            throw new IllegalArgumentException("Couldn't find address for id = " + id);
        }
        return optionalUserAddress.get();
    }
}
