package avachat.learn.netty;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataTransmitter implements Runnable {

    // genrate buffer sizes
// 0, 1, 20, 8K, 16K
    private static final int[] all_buf_sizes = new int[]{0, 1, 2, 5, 8, 10, 15, 20, 8 * 1024, 16 * 1024};
//private static final int [] all_buf_sizes = new int[] {2, 5} ;

    // generate lists of element counts
// null, 0, 1, 2, 5, 8
    private static final int[] all_list_sizes = new int[]{-1, 0, 1, 2, 8};
//private static final int [] all_list_sizes = new int[] {1, 2} ;

    Random random = new Random(System.currentTimeMillis());


    // hack : to quickly implement a global flag
    public static volatile int LastMessageType = 0;


    public void run() {

        while (true) {

            if (MyNettyClient.ExitFlag) {
                System.out.println("Stopping DataTransmitter thread due to ExitFlag");
                return;
            } //

            // look up the channel handler
            Transport transport = Transport.getInstance();

            if (transport != null) {
                // send an object
                if (Math.random() > 0.5) {
                    Serializable obj = new MessageType1();
                    System.out.println("Sending object " + obj);
                    transport.sendObject(obj, generateBufferList());
                    LastMessageType = 1;
                } //
                else {
                    Serializable obj = new MessageType2();
                    System.out.println("Sending object " + obj);
                    transport.sendObject(obj, generateBufferList());
                    LastMessageType = 2;
                } //
            } //
            else {
                System.out.println("Channel not established yet");
            } //

            // sleep for a while
            System.out.println("Sleeping");
            try {
                Thread.sleep(5000);
            }// try
            catch (Exception e) {
                System.out.println("Exception while sleeping " + e.toString());
            }//catch

            System.out.println("==========");
            System.out.println("");
            System.out.println("");

        } //

    } //

    private List<ByteBuffer> generateBufferList() {

        int list_size_index = random.nextInt(all_list_sizes.length);
        //int list_size = all_list_sizes [list_size_index] ;
        int list_size = 13;

        if (list_size == -1) {
            return null;
        } //

        List<ByteBuffer> buf_list = new ArrayList<ByteBuffer>(list_size);

        for (int i = 0; i < list_size; i++) {
            buf_list.add(generateBuffer());
        } //

        return buf_list;
    } //


    private ByteBuffer generateBuffer() {

        int buf_size_index = random.nextInt(all_buf_sizes.length);
        //int buf_size = all_buf_sizes [buf_size_index] ;
        int buf_size = 8192;

        byte[] buf = new byte[buf_size];

        // now populate the byte array
        // populate the entire byte array with the same character
        char c = (char) ((int) 'a' + buf_size_index);
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) c;
        } //

        //System.out.println ("Generated " + Arrays.toString(buf)) ;

        return ByteBuffer.wrap(buf);
    } //

} // 


