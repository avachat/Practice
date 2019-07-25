package avachat.warehouse;

import avachat.failure.Params;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class WarehouseClient {

    private final RemoteServerNaive remoteServerNaive;

    public WarehouseClient(@Autowired RemoteServerNaive remoteServerNaive) {
        this.remoteServerNaive = remoteServerNaive;
    }


    public boolean callRemoteServerNaive (long orderId, Map<String, Params> failureInjection) {
        log.info("Calling NAIVE server for order " + orderId);
        boolean result = remoteServerNaive.processRequestNaive(orderId, failureInjection);
        log.info("NAIVE server returned " + (result?"SUCCESS":"FAILURE"));
        return result;
    }

}
