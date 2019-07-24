package avachat.persistence.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
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

}
