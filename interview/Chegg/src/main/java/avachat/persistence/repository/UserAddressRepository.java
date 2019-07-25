package avachat.persistence.repository;

import avachat.persistence.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    List<UserAddress> findAllByUserUserId(Long userId);
}
