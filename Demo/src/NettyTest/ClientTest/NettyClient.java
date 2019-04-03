package NettyTest.ClientTest;

import NettyTest.ServerTest.FsServer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Created by nick on 2018/3/8.
 */
public class NettyClient implements Runnable{
    private final String host;
    private final int port;

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        try{
            this.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    //发送消息无延迟
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            //添加解码器
                            ch.pipeline().addLast("frameDecoder",
                                    new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,0));
                            //添加编码器
                            ch.pipeline().addLast("frameEncoder",
                                    new LengthFieldPrepender(4,false));

                            //客户端业务实现
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static String HOST = "127.0.0.1";
//    public static String HOST = "219.223.192.60";
//    public static int PORT = FsServer.FSSERVER_DEFAULT_PORT;
    public static int PORT = 10087;


    //                            ch.pipeline().addLast("stringDecoder",new StringDecoder(CharsetUtil.UTF_8));
//                            ch.pipeline().addLast("stringEncoder",new StringEncoder(CharsetUtil.UTF_8));
    public static void main(String[] args) throws Exception {
        new NettyClient(HOST, PORT).start();
    }
}
