package avachat.persistence.service;

import avachat.persistence.entity.User;
import avachat.persistence.entity.UserAddress;
import avachat.persistence.repository.UserAddressRepository;
import avachat.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserAddressService {

    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    public UserAddressService(UserRepository userRepository, UserAddressRepository userAddressRepository) {
        this.userRepository = userRepository;
        this.userAddressRepository = userAddressRepository;
    }

    public UserAddress createOne(long userId, UserAddress userAddress) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new IllegalArgumentException("Unknown userId = " + userId);
        }
        userAddress.setUser(optionalUser.get());
        return userAddressRepository.save(userAddress);
    }

    public List<UserAddress> findAll() {
        return userAddressRepository.findAll();
    }

}
