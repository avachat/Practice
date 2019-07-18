package avachat.learn.concurrency.txn;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockedSerializer implements TxnSerializer {


    private static final int EXPECTED_NUM_RESOURCES = 1000;
    private static final float LOAD_FACTOR = 0.75f;
    private static final int EXPECTED_CONCURRENCY_LEVEL = 32;

    private ExecutorService txnExecutorService;
    private Lock mapLock;
    private ConcurrentMap<String, Queue<TxnTask>> resourceMap;

    public LockedSerializer(ExecutorService txn_executor) {

        this.txnExecutorService = txn_executor;
        this.mapLock = new ReentrantLock();
        this.resourceMap = new ConcurrentHashMap<String, Queue<TxnTask>>
            (EXPECTED_NUM_RESOURCES, LOAD_FACTOR, EXPECTED_CONCURRENCY_LEVEL);

    } //


    public Future addTxn(String resource_id, TxnTask txn_task) {

        Future f = null;

        // lock the map
        mapLock.lock();

        try {
            Queue<TxnTask> txn_queue;
            if (resourceMap.containsKey(resource_id)) {
                // transaction are already in progress for this resource
                txn_queue = resourceMap.get(resource_id);
                // add the task to the queue
                txn_queue.add(txn_task);
                f = null; // no task has been submitted
            } else {
                // create the Txn queue
                txn_queue = new LinkedBlockingQueue<TxnTask>();
                // put it in the map
                resourceMap.put(resource_id, txn_queue);
                // add the task to the queue
                txn_queue.add(txn_task);
                // since it's the only task for this resurce,
                // immidietely submit it for execution
                f = txnExecutorService.submit(txn_task);
            } //
        }// try
        finally {
            // always unlock the map
            mapLock.unlock();
        }

        return f;

    }


    public Future removeTxn(String resource_id, String txn_id) {

        Future f = null;

        // lock the map
        mapLock.lock();

        try {
            // get the transactions queue for this resource
            Queue<TxnTask> txn_queue = resourceMap.get(resource_id);
            assert (txn_queue != null); // there must be a queue
            // remove the first task in the queue
            TxnTask current_txn_task = txn_queue.remove();
            assert (current_txn_task != null); // the queue must have at least one task
            assert (current_txn_task.getTxnId() == txn_id); // it must be the same task that just ended
            // check if there is a task in line for this resource id
            TxnTask next_txn_task = txn_queue.peek();
            if (next_txn_task != null) {
                // if there is submit it for execution
                f = txnExecutorService.submit(next_txn_task);
            } else {
                // no other task is waiting for this resource
                // remove the queue from the map
                resourceMap.remove(resource_id);
                f = null; // no task was submitted
            } //
        } finally {
            // always unlock the map
            mapLock.unlock();
        } //

        return f;
    }

} // 
