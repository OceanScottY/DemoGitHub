package JavaDemoTest.NetDemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @文件描述： socketServer  demo，与SocketDemo文件相互搭配
 * @创建者：
 * @创建日期：2019/3/18
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class SocketServerDemo {

    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(12345);
        Socket s = ss.accept();
        InputStream is = s.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        //读取长度
        int lenData = dis.readInt();
        byte[] dataByte = new byte[lenData];
        dis.read(dataByte);
        System.out.println("服务器收到的数据是："+new String(dataByte));
        //往客户端发送数据
        String msgToSendStr = "{\"type\":-10,\"msg\":\"我收到数据了\"}";

        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        byte[] msgToSend = msgToSendStr.getBytes();
        dos.writeInt(msgToSend.length);
        dos.write(msgToSend);

        os.close();
        s.close();
    }

}
