package avachat.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class UserAddress {

    /*
    Table created with

    CREATE TABLE  chegg.user_address (
        address_id BIGINT NOT NULL PRIMARY KEY,
        user_id BIGINT NOT NULL,
        first_name VARCHAR(40) DEFAULT NULL,
        last_name VARCHAR(40) DEFAULT NULL,
        line1 VARCHAR(255) NOT NULL,
        line2 VARCHAR(35) DEFAULT NULL,
        city VARCHAR(100) DEFAULT NULL,
        state VARCHAR(50) NOT NULL,
        zip VARCHAR(10) DEFAULT NULL,
        country VARCHAR(20) NOT NULL DEFAULT 'US',
        created_by VARCHAR(8) DEFAULT 'dummy',
        created_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        modified_by VARCHAR(8) DEFAULT 'dummy',
        modified_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,

        CONSTRAINT fk_user FOREIGN KEY ("user_id") REFERENCES chegg.user ("user_id")
    );

     */

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chegg_useraddress_addressrid_seq")
    @SequenceGenerator(name="chegg_useraddress_userid_seq", sequenceName = "chegg_useraddress_addressrid_seq", allocationSize = 1)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String firstName;
    private String lastName;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zip;
    private String country;

    protected UserAddress() {}

}
