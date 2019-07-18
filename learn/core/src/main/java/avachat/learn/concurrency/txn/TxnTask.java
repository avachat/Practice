package avachat.learn.concurrency.txn;

import java.util.concurrent.Callable;

public interface TxnTask extends Callable {

    public String getTxnId();

} // 
