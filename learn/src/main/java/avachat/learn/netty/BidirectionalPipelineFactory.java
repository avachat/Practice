package avachat.learn.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.jboss.netty.handler.codec.frame.LengthFieldPrepender;

public class BidirectionalPipelineFactory
    implements ChannelPipelineFactory {

    private boolean isServerside;

    public BidirectionalPipelineFactory(boolean is_serverside) {
        isServerside = is_serverside;
    }

    public ChannelPipeline getPipeline() throws Exception {

        // get the default pipeline
        ChannelPipeline pipeline = Channels.pipeline();

        //pipeline.addLast ("Encoder", new ObjectEncoder()) ;

        int maxFrameLength = 1024 * 1024;
        int lengthFieldOffset = 0;
        int lengthFieldLength = 4;
        int lengthAdjustment = 0;
        int initialBytesToStrip = 4;
        pipeline.addLast("LengthFrameDecoder",
                         new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                                                          lengthAdjustment, initialBytesToStrip));
        pipeline.addLast("SysOut", new ServerPOJOHandler(isServerside));

        pipeline.addLast("LengthPrepender", new LengthFieldPrepender(4));
        //System.out.println ("Adding ClientPOJO handler to the pipeline") ;
        pipeline.addLast("ClientPOJO", new ClientPOJOHandler());

        return pipeline;

    } //


} // 


