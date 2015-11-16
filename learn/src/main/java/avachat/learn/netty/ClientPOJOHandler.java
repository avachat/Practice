package avachat.learn.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelDownstreamHandler;

public class ClientPOJOHandler
    extends SimpleChannelDownstreamHandler {


    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent ch_state_event) {

        // write first object
        //System.out.println ("Writing first object on channel connect") ;
        //ch_state_event.getChannel().write ("Hello World from POJO client") ;

        System.out.println("ClientPOJOHnadler received channel connect");
        //Transport.init (ch_state_event.getChannel()) ;
    }


    public void messageReceived(ChannelHandlerContext channel_context, MessageEvent msg_event) {

        Object obj = msg_event.getMessage();

        System.out.println("ClientPOJOHandler received object of class " + obj.getClass().getName());
    } //


} // 



