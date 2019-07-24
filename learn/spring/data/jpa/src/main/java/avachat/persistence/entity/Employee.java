package avachat.persistence.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long employeeId;
    private String name;

    // for JPA
    protected Employee() {}

    public Employee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID = " + employeeId + ", name = " + name;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
