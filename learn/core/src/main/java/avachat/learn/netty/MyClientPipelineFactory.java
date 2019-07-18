package avachat.learn.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class MyClientPipelineFactory
    implements ChannelPipelineFactory {


    public ChannelPipeline getPipeline() throws Exception {

        // get the default pipeline
        ChannelPipeline pipeline = Channels.pipeline();

        //pipeline.addLast ("Encoder", new ObjectEncoder()) ;

        pipeline.addLast("LengthPrepender", new LengthFieldPrepender(4));

        System.out.println("Adding ClientPOJO handler to the pipeline");
        pipeline.addLast("ClientPOJO", new ClientPOJOHandler());

        return pipeline;

    } //


} // 


