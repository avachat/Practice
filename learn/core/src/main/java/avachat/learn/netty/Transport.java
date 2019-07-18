package avachat.learn.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class Transport {

/* NOT thread safe */

    private static Transport TheInstance = null;

    public static Transport init(Channel ch) {
        TheInstance = new Transport(ch);
        return TheInstance;
    }

    public static Transport getInstance() {
        return TheInstance;
    }

    private Channel channel = null;

    private Transport(Channel ch) {
        channel = ch;
    } //

    public void sendObject(Serializable obj, List<ByteBuffer> buf_list) {

        try {
            byte[] serialized_obj = serializeObjectToBytes(obj);
            System.out.println("Serialized object to byte array size = " + serialized_obj.length);

            // format :
            // 4 bytes : length of serialized object
            // serialized object
            // num opf buffers
            // length of each buffer
            // all the buffers

            // serialized object (length, bytes)
            ChannelBuffer buf_length_serialized_obj = ChannelBuffers.buffer(4);
            buf_length_serialized_obj.writeInt(serialized_obj.length);
            // now the object
            ChannelBuffer buf_serialized_obj = ChannelBuffers.wrappedBuffer(serialized_obj);

            // now the data
            ChannelBuffer buf_serialized_data = serializeDataBuffers(buf_list);

            // wrap it all together
            ChannelBuffer
                buf_all =
                ChannelBuffers.wrappedBuffer(buf_length_serialized_obj, buf_serialized_obj, buf_serialized_data);

            channel.write(buf_all);

        }// try
        catch (Exception e) {
            System.out.println("Exception " + e.toString());
        }//catch
    } //

    public void sendObjectDontUse(Serializable obj) {

	/*
    String obj_str = serialize (obj) ;
	if ( obj_str != null ) {
	    Serializable obj2 = deserialize (obj_str) ;
	    if ( obj2 != null ) {
		System.out.println ("============== Actually writing") ;
		System.out.println (obj_str) ;
		System.out.println () ;
		channel.write (obj_str) ;
	    } // 
	} // 
	*/

        try {
            byte[] buf = serializeObjectToBytes(obj);
            channel.write(ChannelBuffers.wrappedBuffer(buf));
        }// try
        catch (Exception e) {
            System.out.println("Exception " + e.toString());
        }//catch
    } //


    private ChannelBuffer serializeDataBuffers(List<ByteBuffer> buf_list) {

        if ((buf_list == null) || (buf_list.size() == 0)) {
            ChannelBuffer buf_data_lengths = ChannelBuffers.buffer(4);
            buf_data_lengths.writeInt(0);
            return buf_data_lengths;
        } //

        int num_buffers = buf_list.size();
        System.out.println("Num of buffers to serialize = " + num_buffers);

        // pre-pend the data with the sizes
        // this has one int for num of buffers
        // then one int per buffer (for its size)
        // one int has 4 bytes
        ChannelBuffer ch_buf_size_info = ChannelBuffers.buffer(4 * (num_buffers + 1));
        ch_buf_size_info.writeInt(num_buffers);

        // now iterate over the list of buffers to put them in the big wrapped channel buffer
        ChannelBuffer data_buf = null;
        int total_data_size = 0;
        int i = -1;
        for (ByteBuffer buf : buf_list) {
            // write the size into the size_info buffer
            ++i;
            int size = buf.remaining();
            total_data_size += size;
            ch_buf_size_info.writeInt(size);
            if (size <= 0) {
                System.out.println("Ignoring data buf with 0 size");
                continue;
            } //
            char c = (char) (buf.get(0));
            System.out.println("Adding buffer of size " + size + " filled with char " + c);
            if (data_buf == null) {
                // first time
                // create a new wrapped buffer
                data_buf = ChannelBuffers.wrappedBuffer(buf);
            } //
            else {
                // append buf to existing data_buf
                data_buf = ChannelBuffers.wrappedBuffer(data_buf, ChannelBuffers.wrappedBuffer(buf));
            } //
        } //

        //System.out.println ("Data wrapped into " + data_buf.toString ("UTF-8")) ;

        // validate
        if (data_buf.readableBytes() != total_data_size) {
            System.out
                .println("BUG : data_buf.remaining " + data_buf.readableBytes() + " != total_size " + total_data_size);
        } //

        ChannelBuffer ch_buf_data_with_size_info = ChannelBuffers.wrappedBuffer(ch_buf_size_info, data_buf);

        // validate
        int expected_size = (4 * (num_buffers + 1)) + total_data_size;
        int actual_size = ch_buf_data_with_size_info.readableBytes();
        if (expected_size != actual_size) {
            System.out.println(
                "BUG : Buffer wraping failed : actual_size " + actual_size + " != expected_size " + expected_size);
        } //
        else {
            System.out.println(
                "Validated : Buffer wraping : actual_size " + actual_size + " == expected_size " + expected_size);
        } //

        return ch_buf_data_with_size_info;

    } //

    private byte[] serializeObjectToBytes(Serializable obj) {

        try {
            System.out.println("Serializing obj " + obj);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();
        }// try
        catch (Exception e) {
            System.out.println("Exception " + e.toString());
            e.printStackTrace();
        }//catch

        return null;
    } //

    private String serialize(Serializable obj) {
        String obj_str = null;

        try {
            System.out.println("Serializing obj " + obj);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();

            // validate
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Object obj2 = ois.readObject();
                System.out.println("Validate quick deserialization " + obj2.toString());
                ois.close();
                bais.close();
            }// try
            catch (Exception e) {
                System.out.println("Exception " + e.toString());
                e.printStackTrace();
                return null;
            }//catch

            byte[] baos_bytes = baos.toByteArray();
            obj_str = new String(baos_bytes, "UTF-8");

            // compare arrays
            if (!Arrays.equals(baos_bytes, obj_str.getBytes("UTF-8"))) {
                System.out.println("Byte arrays are not same");
                return null;
            } //


        }// try
        catch (Exception e) {
            System.out.println("Exception " + e.toString());
            e.printStackTrace();
        }//catch

        return obj_str;
    } //

    private Serializable deserialize(String obj_str) {

        Object obj = null;

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(obj_str.getBytes());
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
            ois.close();
            bais.close();
        } catch (Exception e) {
            System.out.println("Exception " + e);
            e.printStackTrace();
        } //

        return (Serializable) obj;
    } //

} // 



