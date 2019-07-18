package avachat.learn.concurrency.txn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class UnsafeSerializer implements TxnSerializer {


    private ExecutorService txnExecutorService;

    public UnsafeSerializer(ExecutorService txn_executor) {

        this.txnExecutorService = txn_executor;

    } //


    public Future addTxn(String resource_id, TxnTask txn_task) {

        return txnExecutorService.submit(txn_task);

    }


    public Future removeTxn(String resource_id, String txn_id) {

        return null;

    }

} // 
