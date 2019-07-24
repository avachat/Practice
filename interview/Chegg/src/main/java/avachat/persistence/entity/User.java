package avachat.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class User {


    /*
    Table created using :
    CREATE TABLE chegg.user (
        user_id BIGINT NOT NULL PRIMARY KEY,
        login_handle VARCHAR(20) NOT NULL,
        is_active BOOLEAN NOT NULL
    );

     */

    // TODO : Not sure why a sequence name has to be specified here, and not in other example in package learn.
    // TODO : Could be because of the table name being 'user', and that conflicts somehow with postgres user?
    // TODO : Without the explicit sequence name, get a runtime error saying hibernate_sequence does not exist
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chegg_user_userid_seq")
    @SequenceGenerator(name="chegg_user_userid_seq", sequenceName = "chegg_user_userid_seq", allocationSize = 1)
    private Long userId;
    private String loginHandle;
    private boolean isActive = true;

    // for JPA
    protected User() {}

    public User(String loginHandle) {
        this.loginHandle = loginHandle;
        this.isActive = true;
    }

}
