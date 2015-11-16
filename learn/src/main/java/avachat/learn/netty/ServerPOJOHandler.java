package avachat.learn.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Wrong class name - as the pipiline has become bidrectional Now even client has this class in the pipeline This will
 * intercept and deserialize every message received
 */
public class ServerPOJOHandler
    extends SimpleChannelUpstreamHandler {


    //private TransportReceiver transportReceiver ;
    private boolean isServerside;

    public ServerPOJOHandler(boolean is_serverside) {
        isServerside = is_serverside;
        //transportReceiver = null ;
    }


    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent ch_state_event) {

        System.out.println("ServerPOJOHnadler received channel connect");

        if (!isServerside) {
            System.out.println("Ignoring channel connect on the client side");
            return;
        } //

        // init transport on server side to use the same channel
        Transport t = Transport.init(ch_state_event.getChannel());

        // Init a receiver which can echo back on the same transport
        //transportReceiver = TransportReceiver.init (t) ;
        TransportReceiver tr = TransportReceiver.init(t);
    }


    public void messageReceived(ChannelHandlerContext channel_context, MessageEvent msg_event) {

        Object msg = parseMessage(msg_event);

        TransportReceiver tr = TransportReceiver.getInstance();

        if (tr == null) {
            System.out.println("NO receiver setup");
            return;
        } //

        tr.receiveMessage(msg);
    }


    private Object parseMessage(MessageEvent msg_event) {

    /*
    String obj_str = (String) msg_event.getMessage();
    System.out.println ("Received") ;
    System.out.println (obj_str) ;
    Object obj = null ;

    try {
	ByteArrayInputStream bais = new ByteArrayInputStream (obj_str.getBytes()) ;
	ObjectInputStream ois = new ObjectInputStream (bais) ;
	obj = ois.readObject () ;
	ois.close () ;
	bais.close () ;
    } catch (Exception e) {
    	System.out.println ("Exception " + e) ;
	e.printStackTrace() ;
    } // 
    */

        //String obj_str = (String) msg_event.getMessage();
        //byte[] buf = (byte[]) msg_event.getMessage();

        System.out.println("ServerPOJOHandler received object of class " + msg_event.getMessage().getClass().getName());

        int remaining = 0;
        ChannelBuffer ch_buf = (ChannelBuffer) msg_event.getMessage();
        System.out.println("Readable bytes in the all compposite buffer = " + ch_buf.readableBytes());

        // validate
        remaining = ch_buf.readableBytes();
        if (remaining < 4) {
            System.out.println(
                "Corrupted stream : not even the length of serializaed object present, remaining = " + remaining);
            return null;
        } //

        int length_serialized_obj = ch_buf.readInt();
        System.out.println("Serialized object bytes array size = " + length_serialized_obj);

        // validate
        remaining = ch_buf.readableBytes();
        if (remaining < (length_serialized_obj + 4)) { // serialzed object + num of data buffers
            System.out.println(
                "Corrupted stream : not enough bytes for serializaed object + num of data buffers present, remaining = "
                + remaining);
            return null;
        } //

        //ChannelBuffer buf_serialized_obj = ch_buf.slice(0, length_serialized_obj) ;
        //byte[] bytes_serialized_obj = buf_serialized_obj.array() ;

        byte[] bytes_serialized_obj = new byte[length_serialized_obj];
        ch_buf.readBytes(bytes_serialized_obj);
        System.out.println("Length of the serialized byte representation of object = " + bytes_serialized_obj.length);
        Object obj = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes_serialized_obj);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
            bais.close();
        } catch (Exception e) {
            System.out.println("Exception " + e);
            e.printStackTrace();
        } //

        if (obj != null) {
            System.out.println();
            System.out.println("ServerPOJOHandler received object of class " + obj.getClass().getName());
            System.out.println(String.valueOf(obj));
            System.out.println("==========");
        }

        // advance reading position to data buffers
        //ch_buf.skipBytes (length_serialized_obj) ;

        // read the count of data buffers
        int num_data_buf = ch_buf.readInt();
        System.out.println("Num of data buffers = " + num_data_buf);

        if (num_data_buf == 0) {
            System.out.println("No data buffers");
            return obj;
        } //

        // validate
        remaining = ch_buf.readableBytes();
        if (remaining < (num_data_buf * 4)) { // 4 bytes for each length * num of data buffers
            System.out
                .println("Corrupted stream : not enough bytes for all lengths of data buffers present, expected = "
                         + (num_data_buf * 4) + " but remaining = " + remaining);
            return obj;
        } //

        // now read all the lengths
        long total_length = 0L;
        int[] lengths_of_data_buf = new int[num_data_buf];

        for (int i = 0; i < num_data_buf; i++) {
            lengths_of_data_buf[i] = ch_buf.readInt();
            total_length += lengths_of_data_buf[i];
        } //

        // validate
        remaining = ch_buf.readableBytes();
        if (remaining < (total_length)) { // 4 bytes for each length * num of data buffers
            System.out.println("Corrupted stream : not enough bytes for all data buffers present, expected = "
                               + total_length + " but remaining = " + remaining);
            return obj;
        } //

        //System.out.println ("Remaining data buffer is " + ch_buf.toString("UTF-8")) ;

        List<ByteBuffer> buf_list = new ArrayList<ByteBuffer>();
        for (int length : lengths_of_data_buf) {
            if (length == 0) {
                System.out.println("Ignoring buffer length of zero");
                continue;
            } //
            //System.out.println ("Inspect : ch_buf starts with " + String.valueOf (ch_buf.getByte(ch_buf.readerIndex()))) ;
            ChannelBuffer a_slice = ch_buf.slice(ch_buf.readerIndex(), length);
            //System.out.println ("Inspect : Read a slice : " + a_slice.toString("UTF-8")) ;
            if (a_slice.readableBytes() != length) {
                System.out.println("BUG : slice.readableBytes " + a_slice.readableBytes() + " != length " + length);
                return obj;
            } //
            //System.out.println ("Inspect : a_slice is filled with " + length + " characters of " + (char)a_slice.getByte(0)) ;
            ByteBuffer buf = a_slice.toByteBuffer();
            System.out.println("Inspect : buf.hasArray() " + String.valueOf(buf.hasArray()));
            //char c = (char) (buf.get (0)) ;
            char c = (char) buf.get(buf.position());
            int s = buf.remaining();
            System.out.println("Inspect : buf is filled with " + s + " characters of " + c);
            buf_list.add(buf);
            ch_buf.skipBytes(length);
            //byte b = buf.get() ;
            //System.out.println ("Inspect : buf was filled with " + s + " characters of " + (char)b) ;
        } //

        // validate
        remaining = ch_buf.readableBytes();
        if (remaining != 0) { // all should have been read by now
            System.out.println("Corrupted stream : still remaining = " + remaining);
            return obj;
        } //

        System.out.println("==========");
        System.out.println("");
        System.out.println("");

        return obj;
    } //


} // 



