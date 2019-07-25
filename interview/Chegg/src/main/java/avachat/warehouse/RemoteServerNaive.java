package avachat.warehouse;

import avachat.failure.Params;
import avachat.persistence.entity.RemoteStatus;
import avachat.persistence.repository.RemoteStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@Slf4j
public class RemoteServerNaive {

    private static final String SERVER_NAME = "naive";
    private static final String FAILURE_NAME = "naiveServer";

    private final RemoteStatusRepository remoteStatusRepository;

    public RemoteServerNaive(@Autowired RemoteStatusRepository remoteStatusRepository) {
        this.remoteStatusRepository = remoteStatusRepository;
    }

    @Transactional
    public boolean processRequestNaive(long orderId, Map<String, Params> failureInjection) {

        RemoteStatus remoteStatus = new RemoteStatus(SERVER_NAME, orderId);
        remoteStatusRepository.save(remoteStatus);

        // start working on it
        Params failureParams = failureInjection.getOrDefault(FAILURE_NAME, new Params());
        long delay = failureParams.getDelay();
        if (delay > 0) {
            try {
                log.info("RemoteServerNaive adding a delay of " + delay);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                log.warn("Ignoring " + e);
            }
        } else {
            log.info("RemoteServerNaive NOT adding a delay");
        }

        // finish working on the request
        remoteStatus.setEndedAtToNow();
        // simulate success/failure
        if (failureParams.isFail()) {
            log.info("RemoteServerNaive FAILING processing for " + orderId);
            remoteStatus.setSuccess(false);
            return false;
        } else {
            log.info("RemoteServerNaive SUCCESS processing for " + orderId);
            remoteStatus.setSuccess(true);
        }

        // update local DB
        remoteStatusRepository.save(remoteStatus);

        // return opposite of fail
        return ! failureParams.isFail();
    }
}
