package NettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by Scott on 2018/9/20
 */
public class JsonFrameDecoder  extends ByteToMessageDecoder {

    protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        Long len = in.readLong();


    }


    private class Test{

        private String id;
        private String name;
        private int age;
        private String[] temp;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String[] getTemp() {
            return temp;
        }

        public void setTemp(String[] temp) {
            this.temp = temp;
        }
    }


}
