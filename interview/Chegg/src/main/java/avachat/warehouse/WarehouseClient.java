package avachat.warehouse;

import avachat.failure.Params;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Future;

@Service
@Slf4j
public class WarehouseClient {

    private final RemoteServerNaive remoteServerNaive;
    private final RemoteServerSmart remoteServerSmart;

    public WarehouseClient(@Autowired RemoteServerNaive remoteServerNaive,
                           @Autowired RemoteServerSmart remoteServerSmart) {
        this.remoteServerNaive = remoteServerNaive;
        this.remoteServerSmart = remoteServerSmart;
    }


    public boolean callRemoteServerNaive (long orderId, Map<String, Params> failureInjection) {

        log.info("Calling NAIVE server for order " + orderId);
        boolean result = remoteServerNaive.processRequestNaive(orderId, failureInjection);
        log.info("NAIVE server returned " + (result?"SUCCESS":"FAILURE"));
        return result;
    }



    /*
    public enum RemoteCompletion {
        SUCCESS, FAIL, MAYBE
    }

    public RemoteCompletion callRemoteServerSmart (long orderId, Map<String, Params> failureInjection) {


        // first do a handshake


        // Now request for completion
    }
    */

    @Async
    public Future<Long> initProcessingOnRemoteServerSmart(long orderId,
                                                          Map<String, Params> failureInjection) {

        log.info("Calling SMART server INIT for order " + orderId);
        Long remoteStatusId = remoteServerSmart.initProcessing(orderId, failureInjection);
        log.info("SMART server INIT returned " + remoteStatusId);
        return new AsyncResult<>(remoteStatusId);
    }


    @Async
    public Future<Boolean> processOnRemoteServerSmart(long remoteStatusId,
                                                      Map<String, Params> failureInjection) {

        log.info("Calling SMARt server PROCESS for status id " + remoteStatusId);
        Boolean success = remoteServerSmart.processRequestSmart(remoteStatusId, failureInjection);
        log.info("SMART server PROCESS returned " + success);
        return new AsyncResult<>(success);
    }

}
