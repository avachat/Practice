package avachat.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@ToString
public class StatusTracker {

    /*
    Table created with

    CREATE TABLE chegg.status_tracker (
        status_tracker_id BIGINT NOT NULL PRIMARY KEY,
        status VARCHAR(32) NOT NULL,
        remote_status_id BIGINT NOT NULL,
        old_order_address_id BIGINT NOT NULL,
        new_order_address_id BIGINT NOT NULL,
        started_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        ended_at TIMESTAMP
    );

    alter table status_tracker add column order_id bigint not null;
    */

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chegg_statustracker_seq")
    @SequenceGenerator(name="chegg_statustracker_seq", sequenceName = "chegg_statustracker_seq", allocationSize = 1)
    private Long statusTrackerId;

    private String status; // TODO : Make this an enum
    private Long remoteStatusId;
    private Long orderId;
    private Long oldOrderAddressId;
    private Long newOrderAddressId;
    private Timestamp startedAt;
    private Timestamp endedAt;

    protected StatusTracker() {}

    public StatusTracker(String status,
                         Long remoteStatusId,
                         Long orderId,
                         Long oldOrderAddressId,
                         Long newOrderAddressId) {
        this.status = status;
        this.remoteStatusId = remoteStatusId;
        this.orderId = orderId;
        this.oldOrderAddressId = oldOrderAddressId;
        this.newOrderAddressId = newOrderAddressId;
        this.startedAt = new Timestamp(System.currentTimeMillis());
    }

    public void setEndedAtToNow() {
        this.endedAt = new Timestamp(System.currentTimeMillis());
    }
}
