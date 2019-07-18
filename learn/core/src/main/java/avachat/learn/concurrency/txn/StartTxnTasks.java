package avachat.learn.concurrency.txn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class StartTxnTasks {

    public static final int NUM_THREADS = 500;
    public static final int MAX_TXN_COUNT = 10;
    public static final int MAX_NUM_PARALLEL_TASKS = 1000;
    public static final int NUM_RESOURCES = 1000000; // 10 Million
    public static final int DEFAULT_SLEEP_TIME_MILLIS = 10;
    public static final int DEFAULT_SLEEP_TIME_NANOS = 0;


    /**
     * Usage java txn/StartTxnTasks 0|1|2 numTaskThreads maxNumTasks numParallelTasks numResources
     */

    public static void main(String[] argv) {

        int serializerType = 1;
        int numThreads = NUM_THREADS;
        int maxTxnCount = MAX_TXN_COUNT;
        int maxNumParallelTasks = MAX_NUM_PARALLEL_TASKS;
        int numResources = NUM_RESOURCES;
        int sleepTimeMillis = DEFAULT_SLEEP_TIME_MILLIS;
        int sleepTimeNanos = DEFAULT_SLEEP_TIME_NANOS;

        // -----
        // read the args
        //
        try {
            switch (argv.length) {
                case 3:
                    maxTxnCount = Integer.parseInt(argv[2]);
                case 2:
                    numThreads = Integer.parseInt(argv[1]);
                case 1:
                    serializerType = Integer.parseInt(argv[0]);
                case 0:
                    break;
            } //
        } catch (Exception e) {
            System.out.println("Exception while parsing args " + e);
            return;
        } //

        //
        // -----

        // create an executor service with fixed thread pool size
        ExecutorService theExecutorService = Executors.newFixedThreadPool(numThreads);

        // create a transaction serializer
        TxnSerializer theTxnSerializer = null;
        switch (serializerType) {
            case 0:
                System.out.println("Creating unsafe serializer");
                theTxnSerializer = new UnsafeSerializer(theExecutorService);
                break;
            case 1:
                System.out.println("Creating locked serializer");
                theTxnSerializer = new LockedSerializer(theExecutorService);
                break;
            default:
                System.out.println("Incorrect Serializer");
                theExecutorService.shutdown();
                return;
        } //

        // create a queue for holding futures of submitted tasks
        Queue<Future<?>> queueForFutures = new LinkedList<Future<?>>();

        long start_time = System.nanoTime();

        int current_resource_num = 0;
        for (int txn_count = 0; txn_count < maxTxnCount; txn_count++) {

            // create the tasks upto the limit
            if (queueForFutures.size() >= maxNumParallelTasks) {

                // max num of parallel tasks in progress
                // wait for the first one to finish
                Future<?> f = queueForFutures.remove();
                Object o = null;
                try {
                    o = f.get(); // waits for the task to finish
                }// try
                catch (Exception e) {
                    System.out.println("Exception while waiting for a future " + e.toString());
                    theExecutorService.shutdown();
                    return;
                }//catch

                if (o != null) {
                    // another task was scheduled by the task that just ended
                    queueForFutures.add((Future<?>) o);
                    // go back in the loop
                    // cannot add task till there is room in the queue
                    continue;
                } //
            }

            // cycle through the next resource (modulo operation)
            //String resource_id = String.valueOf (current_resource_num % numResources) ;
            String resource_id = String.valueOf(current_resource_num);

            // create a new transaction task
            TxnTask txn_task = new TxnTaskDummyImpl(theTxnSerializer, resource_id,
                                                    String.valueOf(txn_count), sleepTimeMillis, sleepTimeNanos);

            // submit for serialized execution and get its future
            Future f = theTxnSerializer.addTxn(resource_id, txn_task);

            // add it to the queue to indicate that a task might be running
            queueForFutures.add(f);

            // increament the resource num
            current_resource_num++;

        }

        // all transactions submitted
        // wait for all of them to finish
        for (Future<?> f : queueForFutures) {
            try {
                Future<?> f2 = (Future<?>) f.get();
                // if f had started a task
                // wait for it to finish
                while (f2 != null) {
                    System.out.println("Waiting recursively");
                    f2 = (Future<?>) f2.get();
                } //
            }// try
            catch (Exception e) {
                theExecutorService.shutdown();
                System.out.println("Exception while shutting down " + e.toString());
                return;
            }//catch
        } //

        // shutdown all the threads
        theExecutorService.shutdown();

        long total_time_nanos = System.nanoTime() - start_time;

        System.out.println("TOTAL time " + total_time_nanos / (1000.00 * 1000.00 * 1000.00) + "secs = "
                           + total_time_nanos / (1000.00 * 1000.00) + " millis = "
                           + total_time_nanos + " nanos");

    } // main


}


