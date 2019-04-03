package JavaDemoTest.IODemo.SeDemo;

import java.io.*;
import java.util.Enumeration;
import java.util.Vector;

/**
 * @文件描述： 序列流
 * @创建者：
 * @创建日期：2019/3/18
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Demo {


    //demo1   demo2   序列流
    public static void demo1() throws Exception{


        FileInputStream fis1 = new FileInputStream("./text/a.txt");
        FileInputStream fis2 = new FileInputStream("./text/b.txt");

        SequenceInputStream sis = new SequenceInputStream(fis1,fis2);

        FileOutputStream fos = new FileOutputStream("./text/new.txt");
        DataInputStream dis = new DataInputStream(sis);
        DataOutputStream dos = new DataOutputStream(fos);
        byte[] temp = new byte[1024];
        int len = 0;
        while((len = dis.read(temp)) != -1){
            dos.write(temp,0,len);
        }

        dis.close();
        dos.close();

    }

    public static void demo2()throws Exception{

        FileInputStream fis1 = new FileInputStream("./text/a.txt");
        FileInputStream fis2 = new FileInputStream("./text/b.txt");
        FileInputStream fis3 = new FileInputStream("./text/new.txt");

        Vector<InputStream> v = new Vector<>();
        v.add(fis1);
        v.add(fis2);
        v.add(fis3);

        Enumeration<InputStream> en = v.elements();

        SequenceInputStream sis = new SequenceInputStream(en);
        DataInputStream dis = new DataInputStream(sis);
        FileOutputStream fos = new FileOutputStream("./text/multiTest.txt");
        DataOutputStream dos = new DataOutputStream(fos);

        byte[] buf = new byte[1024];
        int len = 0;
        while((len = dis.read(buf)) != -1){
            dos.write(buf,0,len);
        }

        dis.close();
        dos.close();

    }


    //demo3  内存输出流
    public static void demo3()throws Exception{
        FileInputStream fis = new FileInputStream("./text/a.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataInputStream dis = new DataInputStream(fis);

        byte[] buf = new byte[1024];
        int len = 0;
        while((len = dis.read(buf)) != -1){
            baos.write(buf,0,len);
        }
        byte[] arr = baos.toByteArray();
        System.out.println(new String(arr,"gbk"));

    }


    public static void main(String[] args) throws Exception{
//        demo1();
//        demo2();
        demo3();
    }



}
