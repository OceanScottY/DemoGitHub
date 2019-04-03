package NettyTest.ClientTest;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;

/**
 * Created by Scott on 2018/10/20
 */
public class TestTask implements Runnable {

    private final ChannelHandlerContext ctx;

    public TestTask(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        //do something
        ctx.writeAndFlush(Unpooled.copiedBuffer(String.valueOf(System.currentTimeMillis()), CharsetUtil.UTF_8));
    }
}
