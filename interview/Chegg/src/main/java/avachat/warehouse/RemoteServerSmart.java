package avachat.warehouse;

import avachat.failure.Params;
import avachat.persistence.entity.RemoteStatus;
import avachat.persistence.repository.RemoteStatusRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class RemoteServerSmart {

    private static final String SERVER_NAME = "smart";
    private static final String FAILURE_NAME_INIT = "smartServerInit";
    private static final String FAILURE_NAME_PROCESS = "smartServerProcess";

    private final RemoteStatusRepository remoteStatusRepository;

    public RemoteServerSmart(RemoteStatusRepository remoteStatusRepository) {
        this.remoteStatusRepository = remoteStatusRepository;
    }

    /**
     * Handshake method to establish a distributed transaction.
     *
     * NOTE : For convenience this method returns a Long instead of a detailed server response.
     *
     * @param orderId
     * @param failureInjection
     * @return
     */
    @Transactional
    public Long initProcessing (long orderId, Map<String, Params> failureInjection) {

        // start working on the processing request
        // perform validation checks etc

        Params failureParams = failureInjection.getOrDefault(FAILURE_NAME_INIT, new Params());

        long delay = failureParams.getDelay();
        if (delay > 0) {
            try {
                log.info("RemoteServerSmart:INIT adding a delay of " + delay);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                log.warn("Ignoring " + e);
            }
        } else {
            log.info("RemoteServerSmart:INIT NOT adding a delay");
        }

        if (failureParams.isFail()) {
            // NOTE : This is a remote server. It cannot throw exceptions to a remote client
            log.info("RemoteServerSmart:INIT FAILING");
            return null;
        }

        log.info("RemoteServerSmart:INIT SUCCEED");
        RemoteStatus remoteStatus = new RemoteStatus(SERVER_NAME, orderId);
        remoteStatusRepository.save(remoteStatus);
        return remoteStatus.getRemoteStatusId();
    }


    /**
     * Second and final step of the distributed transaction.
     *
     * NOTE : Returns a boolean for convenience, instead of a proper server response.
     *
     * @param remoteStatusId
     * @param failureInjection
     * @return
     */
    @Transactional
    public Boolean processRequestSmart(long remoteStatusId, Map<String, Params> failureInjection) {

        Optional<RemoteStatus> optionalRemoteStatus = remoteStatusRepository.findById(remoteStatusId);
        if (! optionalRemoteStatus.isPresent()) {
            log.error("RemoteServerSmart : Unknown remoteStatusId " + remoteStatusId);
            return false;
        }

        RemoteStatus remoteStatus = optionalRemoteStatus.get();
        long orderId = remoteStatus.getOrderId();

        // start working on it
        Params failureParams = failureInjection.getOrDefault(FAILURE_NAME_PROCESS, new Params());
        long delay = failureParams.getDelay();
        if (delay > 0) {
            try {
                log.info("RemoteServerSmart adding a delay of " + delay);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                log.warn("Ignoring " + e);
            }
        } else {
            log.info("RemoteServerSmart NOT adding a delay");
        }

        // finish working on the request
        remoteStatus.setEndedAtToNow();
        // simulate success/failure
        if (failureParams.isFail()) {
            log.info("RemoteServerSmart FAILING processing for " + orderId);
            remoteStatus.setSuccess(false);
            return false;
        } else {
            log.info("RemoteServerSmart SUCCESS processing for " + orderId);
            remoteStatus.setSuccess(true);
        }

        // update local DB
        remoteStatusRepository.save(remoteStatus);

        // return opposite of fail
        return ! failureParams.isFail();
    }


    public enum RemoteServerProcessingStatus {
        PROCESS_COMPLETE, PROCESS_FAIL, PROCESS_ONGOING, PROCESS_NOT_FOUND
    }

    /**
     * Find out status of a processing request that was submitted via distributed transaction methods above.
     *
     * NOTE : For convenience this method returns an enum.
     *
     * @param remoteStatusId
     * @param failureInjection
     * @return
     */
    @Transactional
    public RemoteServerProcessingStatus retrieveStatus(long remoteStatusId,
                                                       Map<String, Params> failureInjection) {


        // TODO : Use failure injection to add delays for demonstration purposes

        Optional<RemoteStatus> optionalRemoteStatus = remoteStatusRepository.findById(remoteStatusId);
        if (! optionalRemoteStatus.isPresent()) {
            log.error("RemoteServerSmart : Unknown remoteStatusId " + remoteStatusId);
            return RemoteServerProcessingStatus.PROCESS_NOT_FOUND;
        }

        RemoteStatus remoteStatus = optionalRemoteStatus.get();

        if (remoteStatus.getEndedAt() == null) {
            return RemoteServerProcessingStatus.PROCESS_ONGOING;
        }

        return remoteStatus.isSuccess()
                ? RemoteServerProcessingStatus.PROCESS_COMPLETE
                : RemoteServerProcessingStatus.PROCESS_FAIL;

    }


}
