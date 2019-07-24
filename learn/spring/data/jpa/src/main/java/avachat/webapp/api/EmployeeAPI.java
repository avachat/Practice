package avachat.webapp.api;


import avachat.persistence.entity.Employee;
import avachat.persistence.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class EmployeeAPI {

    private static final Logger log = LoggerFactory.getLogger(EmployeeAPI.class);

    private final EmployeeService employeeService;

    public EmployeeAPI(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @RequestMapping(method = GET, value = "/employees/findAll")
    public List<Employee> findAll () {
        return employeeService.findAll();
    }


    @RequestMapping(method = GET, value = "/employees/findById")
    public List<Employee> findByEmployeeId (@RequestParam long id) {
        return employeeService.findAllById(id);
    }


    @RequestMapping(method = POST, value = "/employees")
    public Employee createOne(@RequestBody Employee employee) {
        //log.debug("POST request for " + employee.toString());
        return employeeService.createOne(employee);
    }

}
