package avachat.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
public class RemoteStatus {

    /*
    Table created with

    CREATE TABLE chegg.remote_status (
        remote_status_id BIGINT NOT NULL PRIMARY KEY,
        server_name VARCHAR(64) NOT NULL,
        started_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
        ended_at TIMESTAMP,
        is_success BOOLEAN,
        reason VARCHAR(64)
    );

    alter table remote_status add column order_id bigint not null;

     */

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "chegg_remotestatus_id_seq")
    @SequenceGenerator(name="chegg_remotestatus_id_seq", sequenceName = "chegg_remotestatus_id_seq", allocationSize = 1)
    private Long remoteStatusId;

    private String serverName;
    private Timestamp startedAt;
    private Timestamp endedAt;
    private boolean isSuccess;
    private String reason;
    private Long orderId;

    protected RemoteStatus() {}

    public RemoteStatus (String serverName, long orderId) {
        this.serverName = serverName;
        this.startedAt = new Timestamp(System.currentTimeMillis());
        this.orderId = orderId;
    }

    public void setEndedAtToNow() {
        this.endedAt = new Timestamp(System.currentTimeMillis());
    }


}
