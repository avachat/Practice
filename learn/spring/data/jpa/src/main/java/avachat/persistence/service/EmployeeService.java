package avachat.persistence.service;

import avachat.persistence.entity.Employee;
import avachat.persistence.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger log  = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;


    public EmployeeService(@Autowired EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        //log.debug("Calling employeeRepository.findAll");
        //List<Employee> result = employeeRepository.findAll();
        //log.debug("Found " + result);
        //return result;
        return employeeRepository.findAll();
    }

    public List<Employee> findAllById(long id) {
        return employeeRepository.findAllByEmployeeId(id);
    }

    public Employee createOne(Employee employee) {
        //log.debug("Saving employee " + employee.toString());
        return employeeRepository.save(employee);
    }
}
