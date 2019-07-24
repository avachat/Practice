package avachat.webapp.api;


import avachat.persistence.entity.Employee;
import avachat.persistence.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class EmployeeAPI {

    private static final Logger log = LoggerFactory.getLogger(EmployeeAPI.class);

    private final EmployeeService employeeService;

    public EmployeeAPI(@Autowired EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @RequestMapping(method = GET, value = "/employee/findAll")
    public List<Employee> findAll () {
        return employeeService.findAll();
    }


    @RequestMapping(method = GET, value = "/employee/findById")
    public List<Employee> findByEmployeeId (@RequestParam long id) {
        return employeeService.findAllById(id);
    }

}
