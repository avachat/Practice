package avachat.learn.netty;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MessageType2 implements java.io.Serializable {

    private static AtomicLong SequenceNum = new AtomicLong();

    private HashMap map;

    public MessageType2() {

        map = new HashMap();
        map.put(SequenceNum.getAndIncrement(), "The value is " + ((int) (Math.floor(10 * Math.random()))));
        map.put(SequenceNum.getAndIncrement(), (int) (Math.floor(10 * Math.random())));
    } //

    public String toString() {
        return map.toString();
    } //

} // 
