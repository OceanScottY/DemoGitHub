package NettyTest.ServerTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Scott on 2018/6/14
 */
public class FsServerHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(FsServerHandler.class);


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent)evt;
        String eventType = null;
        switch (event.state()){
            case READER_IDLE:
                eventType = "读空闲";
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                break;
            case ALL_IDLE:
                eventType = "读写空闲";
                break;
        }
        System.out.println(ctx.channel().remoteAddress() + "超时事件 ：" + eventType);
        super.userEventTriggered(ctx,evt);
        ctx.channel().close();

    }

    /**
     * 客户端与服务端创建连接的时候调用
     * 当前客户端连接服务器的时候，将该链接的channel加入到FsChannelMap.channelMap
     * @param ctx
     * @throws Exception
     */
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String ip = "";
        try {
            ip = ctx.channel().localAddress().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("远程客户端的ip是" + ip);

    }

    /**
     * 客户端与服务端断开连接时调用
     *
     * 当前断开连接的时候，将该链接的channel从FsChannelMap.channelMap中删除
     * @param ctx
     * @throws Exception
     */
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {


    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf in = (ByteBuf) msg;
        int length = in.readInt();
        String v = in.toString(CharsetUtil.UTF_8);

            String response = "{\"type\":2,\"fsid\":2,\"path\":\"/mnt/fs2\",\"timestamp\":\"2018-09-28 15:13:32\",\"fsstate\":0}";
            System.out.println("服务器收到的客户端的信息是 ：" + v);
//            System.out.println("        往客户端发送的信息是 :" + response);
//            ctx.writeAndFlush(response);
//            ctx.writeAndFlush(Unpooled.copiedBuffer(response, CharsetUtil.UTF_8));

    }

    /**
     * 服务端接收客户端发送过来的数据结束之后调用
     * @param ctx
     */
    public void channelReadComplete(ChannelHandlerContext ctx) {
        logger.info("channelReadComplete");
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 出现异常的时候调用
     * @param ctx
     * @param cause
     */
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        //log
        logger.error("Server Channel occur exception.",cause);
        ctx.close();
    }
}
