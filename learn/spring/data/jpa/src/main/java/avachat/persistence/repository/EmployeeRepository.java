package avachat.persistence.repository;

import avachat.persistence.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //List<Employee> findAll();
    List<Employee> findAllByEmployeeId(Long id);
}
