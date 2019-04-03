package NettyTest.ClientTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by nick on 2018/3/8.
 */
//public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
public class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> heartBeat;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

        System.out.println(System.currentTimeMillis());
        System.out.println("要发送的数据是：" + "this is a test");
        ctx.writeAndFlush(Unpooled.copiedBuffer("this is a test", CharsetUtil.UTF_8));
//        this.heartBeat = this.scheduler.scheduleWithFixedDelay(new TestTask(ctx),0,2,TimeUnit.SECONDS);


    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive   连接断开了");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        int  length = in.readInt();
        System.out.println("客户端接受到的数据是: " + in.toString(CharsetUtil.UTF_8));

    }
    public String convertByteBufToString(ByteBuf buf) {
        String str;
        if(buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
