package avachat.persistence.repository;

import avachat.persistence.entity.StatusTracker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTrackerRepository extends JpaRepository<StatusTracker, Long> {
}
