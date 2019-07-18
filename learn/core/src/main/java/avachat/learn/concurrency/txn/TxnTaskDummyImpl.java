package avachat.learn.concurrency.txn;

import java.util.concurrent.Future;

public class TxnTaskDummyImpl implements TxnTask {

    private TxnSerializer txnSerializer;
    private String resourceId;
    private String txnId;
    private long sleepTimeMillis;
    private int sleepTimeNanos;

    public TxnTaskDummyImpl(TxnSerializer txn_serializer, String resource_id, String txn_id, long m, int n) {

        this.txnSerializer = txn_serializer;
        this.resourceId = resource_id;
        this.txnId = txn_id;
        this.sleepTimeMillis = m;
        this.sleepTimeNanos = n;
    }

    public String getTxnId() {
        return txnId;
    }


    public void run() {

        try {
            Object result = this.call();
            System.out.println("Result from the call = " + String.valueOf(result));
        } catch (Exception ignored) {
            System.out.println("Ignoring " + ignored);
        } //
    }

    public Future<?> call() throws Exception {

        //System.out.println ("Running task " + txnId + " for " + resourceId) ;
        try {
            Thread.sleep(sleepTimeMillis, sleepTimeNanos);
        } catch (InterruptedException e) {
            System.out.println("Interrupted task " + txnId + " for " + resourceId);
            throw e;
        } //

        // remove self from the serializer
        Future<?> f = txnSerializer.removeTxn(resourceId, txnId);

        //System.out.println ("Completed task " + txnId + " for " + resourceId) ;

        return f;

    } //


} // 
