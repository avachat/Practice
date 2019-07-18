package avachat.learn.netty;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

public class MyServerPipelineFactory
    implements ChannelPipelineFactory {


    public ChannelPipeline getPipeline() throws Exception {

        // get the default pipeline
        ChannelPipeline pipeline = Channels.pipeline();

        //pipeline.addLast ("Decoder", new ObjectDecoder());

        int maxFrameLength = 1024 * 1024;
        int lengthFieldOffset = 0;
        int lengthFieldLength = 4;
        int lengthAdjustment = 0;
        int initialBytesToStrip = 4;
        pipeline.addLast("LengthFrameDecoder",
                         new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                                                          lengthAdjustment, initialBytesToStrip));

        pipeline.addLast("SysOut", new ServerPOJOHandler(true));

        return pipeline;

    } //


} // 


