package avachat.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Order {


    /*
    Table created with

    CREATE TABLE chegg.order (
        order_id BIGINT NOT NULL PRIMARY KEY,
        order_description VARCHAR(256) NOT NULL
    );

     */

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chegg_order_orderid_seq")
    @SequenceGenerator(name="chegg_order_orderid_seq", sequenceName = "chegg_order_orderid_seq", allocationSize = 1)
    private Long orderId;
    private String orderDescription;

    protected Order() {}

    public Order(String orderDescription) {
        this.orderDescription = orderDescription;
    }

}
