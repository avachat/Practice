package avachat.persistence.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Reconciler {


    @Scheduled(fixedDelay = 60000)
    public void reconcileIncompleteTransactions() {

        log.debug("Reconciler waking up");
    }
}
