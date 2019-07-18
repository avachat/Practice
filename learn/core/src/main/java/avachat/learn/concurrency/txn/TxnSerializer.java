package avachat.learn.concurrency.txn;

import java.util.concurrent.Future;

public interface TxnSerializer {

    public Future addTxn(String resource_id, TxnTask txn_task);

    public Future removeTxn(String resource_id, String txn_id);

} // 
