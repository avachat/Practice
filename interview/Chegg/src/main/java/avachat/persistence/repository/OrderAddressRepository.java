package avachat.persistence.repository;

import avachat.persistence.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {
    List<OrderAddress> findAllByOrderOrderId(long orderId);
    List<OrderAddress> findAllByUserAddressAddressId(long addressId);
}
