package avachat.persistence.service;

import avachat.persistence.entity.User;
import avachat.persistence.entity.UserAddress;
import avachat.persistence.repository.UserAddressRepository;
import avachat.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserAddressService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;

    public UserAddressService(@Autowired UserRepository userRepository, @Autowired UserAddressRepository userAddressRepository) {
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

        /*
        TODO : Why does the following code gives serialization error?
        User user = entityManager.getReference(User.class, userId);
        userAddress.setUser(user);
        UserAddress createdUserAddress = userAddressRepository.save(userAddress);
        long addressId = createdUserAddress.getAddressId();
        log.debug("Created address " + addressId);
        return userAddressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Weird : could not load addressId " + addressId));
        */
    }

    public Optional<UserAddress> findById(long addressId) {
        return userAddressRepository.findById(addressId);
    }

    public List<UserAddress> findByUserId(long userId) {
        return userAddressRepository.findAllByUserUserId(userId);
    }

    public List<UserAddress> findAll() {
        return userAddressRepository.findAll();
    }

}
