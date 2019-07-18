package avachat.learn.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.TimeUnit;
//import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;


public class MyNettyServer {


    public static void main(String[] args) throws Exception {

        ChannelFactory factory = new NioServerSocketChannelFactory(
            Executors.newCachedThreadPool(),
            Executors.newCachedThreadPool()
        );

        ServerBootstrap bootstrap = new ServerBootstrap(factory);

        //bootstrap.setPipelineFactory(new MyServerPipelineFactory()) ;
        bootstrap.setPipelineFactory(new BidirectionalPipelineFactory(true));

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);

        bootstrap.bind(new InetSocketAddress(8019));
    }

} // 
