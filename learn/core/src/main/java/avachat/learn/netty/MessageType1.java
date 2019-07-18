package avachat.learn.netty;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;

public class MessageType1 implements java.io.Serializable {

    private static AtomicLong SequenceNum = new AtomicLong();

    private long someId = -1;
    private String someName = "";
    private byte[] buf = null;

    public MessageType1() {

        someId = SequenceNum.getAndIncrement();
        someName = "The random number is " + ((int) (Math.floor(10 * Math.random())));
        buf = new byte[8];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((char) ((int) (97 + Math.floor(26 * Math.random()))));
        } //

    } //

    public String toString() {
        return "someID=" + someId + ";"
               + " someName=" + someName + ";"
               + " buf=" + Arrays.toString(buf)
            ;
    } //

} // 
