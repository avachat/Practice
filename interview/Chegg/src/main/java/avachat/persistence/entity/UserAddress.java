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
