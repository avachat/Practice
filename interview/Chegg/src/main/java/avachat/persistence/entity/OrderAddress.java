package avachat.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Random;

@Entity
@Getter
@Setter
@ToString
public class OrderAddress {

    /*
    Table created with

    CREATE TABLE chegg.order_address (
        order_address_id BIGINT NOT NULL PRIMARY KEY,
        order_id BIGINT NOT NULL,
        address_id BIGINT NOT NULL,
        line_id BIGINT NOT NULL,

        CONSTRAINT fk_order FOREIGN KEY ("order_id") REFERENCES chegg.order ("order_id"),
        CONSTRAINT fk_address FOREIGN KEY ("address_id") REFERENCES chegg.user_address ("address_id")
    );

     */

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chegg_orderaddress_orderaddressid_seq")
    @SequenceGenerator(name="chegg_orderaddress_orderaddressid_seq", sequenceName = "chegg_orderaddress_orderaddressid_seq", allocationSize = 1)
    private Long orderAddressId;
    private Long lineId;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private UserAddress userAddress;

    protected OrderAddress() {}

    public OrderAddress(UserAddress userAddress, Order order) {
        this.userAddress = userAddress;
        this.order = order;
        this.lineId = 1L;
    }

}
