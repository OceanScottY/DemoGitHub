package NettyTest.ServerTest2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * Created by Scott on 2018/9/20
 */
public class FsServer implements Runnable {

    public static int FSSERVER_DEFAULT_PORT = 10087;
    private Logger logger = LoggerFactory.getLogger(FsServer.class);
    int port;

    public FsServer(int port) {
        this.port = port;
    }
    public FsServer() {
        this.port = FSSERVER_DEFAULT_PORT;
    }

    public void start() throws Exception {
        //在文件系统服务器开启的时候，需要对mounter进行初始化，暂时未做

        //连接处理的group
        EventLoopGroup boss = new NioEventLoopGroup();
        //事件处理的group
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            //绑定处理group
            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            //保持连接数
            bootstrap.option(ChannelOption.SO_BACKLOG,1024);
            //通过NoDelay禁用Nagle,使消息立即发出去，不用等待到一定的数据量才发出去
            bootstrap.option(ChannelOption.TCP_NODELAY,true);
            //保持长连接状态
            bootstrap.option(ChannelOption.SO_KEEPALIVE,true);

            bootstrap.option(ChannelOption.SO_SNDBUF,1024*1024)
                    .option(ChannelOption.SO_RCVBUF,10*1024*1024);
            bootstrap.handler(new LoggingHandler(LogLevel.INFO));
            bootstrap.localAddress(new InetSocketAddress(port));
            //处理新连接
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,0));
                    ch.pipeline().addLast(new LengthFieldPrepender(4,false));
                    //增加业务处理
                    ch.pipeline().addLast(new FsServerHandler());
                }
            });
            logger.info("FsServer is running");
            ChannelFuture f = bootstrap.bind().sync();
            if(f.isSuccess()){
                logger.info("Long connection started success");
            }else {
                logger.info("Long connection started fail");
            }
            f.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully().sync();
            worker.shutdownGracefully().sync();
        }
    }

    @Override
    public void run() {
        try {
            start();
        } catch (Exception e) {
            //log
            logger.error("Netty server stops running because of some exceptions happening.",e);
        }
    }

    public static void main(String[] args) {
        FsServer fsServer = new FsServer();
        Thread myThread = new Thread(fsServer);
        myThread.start();
    }

}
