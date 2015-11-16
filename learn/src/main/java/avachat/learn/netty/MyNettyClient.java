package avachat.learn.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
//import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;


public class MyNettyClient {


    // hack
    public static volatile boolean ExitFlag = false;

    public static void main(String[] args) throws Exception {

        ChannelFactory factory = new NioClientSocketChannelFactory(
            Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool()
        );

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        //bootstrap.setPipelineFactory(new MyClientPipelineFactory()) ;
        bootstrap.setPipelineFactory(new BidirectionalPipelineFactory(false));

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        System.out.println("Connecting to localhost:8019");
        //bootstrap.connect(new InetSocketAddress("127.0.0.1", 4091));
        ChannelFuture cf = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8019));

        // start sending data periodically
        Thread data_thread = new Thread(new DataTransmitter());
        data_thread.start();

        cf.awaitUninterruptibly();

        if (!cf.isDone()) {
            System.out.println("ChannelFuture says not done");
            ExitFlag = true;
        } //

        if (!cf.isSuccess()) {
            System.out.println("ChannelFuture says not successful");
            ExitFlag = true;
        } //

        Transport t = Transport.init(cf.getChannel());
        TransportReceiver tr = TransportReceiver.init(t);

    }

} // 
