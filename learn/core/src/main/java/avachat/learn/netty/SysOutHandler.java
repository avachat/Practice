package avachat.learn.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class SysOutHandler
    extends SimpleChannelHandler {


    public void messageReceived(ChannelHandlerContext channel_context, MessageEvent msg_event) {

        ChannelBuffer buf = (ChannelBuffer) msg_event.getMessage();

        while (buf.readable()) {
            System.out.println((char) buf.readByte());
            System.out.flush();
        } //
    } //


} // 



