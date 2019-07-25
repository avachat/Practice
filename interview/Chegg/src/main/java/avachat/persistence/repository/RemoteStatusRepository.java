package avachat.persistence.repository;

import avachat.persistence.entity.RemoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemoteStatusRepository extends JpaRepository<RemoteStatus, Long> {
}
