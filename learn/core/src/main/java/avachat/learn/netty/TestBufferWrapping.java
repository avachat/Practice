package avachat.learn.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.nio.ByteBuffer;

public class TestBufferWrapping {


    public static void main(String argv[]) {

// create some byte arrays
        int size_a = 2;
        byte[] bytes_a = new byte[size_a];
        for (int i = 0; i < size_a; i++) {
            bytes_a[i] = (byte) 'a';
        } //

        int size_b = 3;
        byte[] bytes_b = new byte[size_b];
        for (int i = 0; i < size_b; i++) {
            bytes_b[i] = (byte) 'a';
        } //

        int size_c = 5;
        byte[] bytes_c = new byte[size_c];
        for (int i = 0; i < size_c; i++) {
            bytes_c[i] = (byte) 'a';
        } //

// create some byte buffers
        ByteBuffer buf_a = ByteBuffer.wrap(bytes_a);
        ByteBuffer buf_b = ByteBuffer.wrap(bytes_b);
        ByteBuffer buf_c = ByteBuffer.wrap(bytes_c);

// debug
        System.out.println("Buf_a ::"
                           + " cap = " + buf_a.capacity()
                           + " pos = " + buf_a.position()
                           + " remain = " + buf_a.remaining()
        );

        System.out.println("Buf_b ::"
                           + " cap = " + buf_b.capacity()
                           + " pos = " + buf_b.position()
                           + " remain = " + buf_b.remaining()
        );

        System.out.println("Buf_c ::"
                           + " cap = " + buf_c.capacity()
                           + " pos = " + buf_c.position()
                           + " remain = " + buf_c.remaining()
        );

// read something out of the buffer
// to adjust the read position
//byte [] tmp = new byte[size_a + size_b + size_c] ;
//ByteBuffer ret = buf_a.get (tmp, 0, buf_a.remaining()) ;

// wrap in Netty buffers

// First
        ChannelBuffer wrapped_buf = ChannelBuffers.wrappedBuffer(buf_a.duplicate().duplicate());
        System.out.println("WRAPPED BUF ::"
                           + " cap = " + wrapped_buf.capacity()
                           + " readable = " + wrapped_buf.readableBytes()
        );

        wrapped_buf = ChannelBuffers.wrappedBuffer(wrapped_buf, ChannelBuffers.wrappedBuffer(buf_b.duplicate()));
        System.out.println("WRAPPED BUF ::"
                           + " cap = " + wrapped_buf.capacity()
                           + " readable = " + wrapped_buf.readableBytes()
        );

    } //


} // 



