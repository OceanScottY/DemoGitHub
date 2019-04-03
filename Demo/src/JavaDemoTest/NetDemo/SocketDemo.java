package JavaDemoTest.NetDemo;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.Socket;

/**
 * @文件描述： socket 发送数据、接收数据   数据格式：长度+报文
 * @创建者：
 * @创建日期：2019/3/17
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class SocketDemo {

    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("127.0.0.1", 12345);
        OutputStream os = socket.getOutputStream();
        String msgToSend = "{\"type\":-10}";
        byte[] data = msgToSend.getBytes();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(data.length);
        byte[] sendData = msgToSend.getBytes();
        // 发送数据长度(4字节)+数据
        os.write(sendData);
        os.flush();
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        int lenRead = dis.readInt();
        byte[] recData = new byte[lenRead];
        dis.read(recData);
        System.out.println("收到的数据是：" + new String(recData));
        os.close();
        socket.close();
    }


}
