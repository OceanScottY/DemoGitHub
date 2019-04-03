package JavaDemoTest.IODemo.StreamDemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/12
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class CopyFileDemo {


    public static void copyFile() throws Exception{
        FileInputStream fis = new FileInputStream("E:\\monitor.sql");
        FileOutputStream fos = new FileOutputStream("E:\\xxxxxxxx.sql",true);
        int a;
        while((a = fis.read()) != -1){

            fos.write(a);
        }
        fis.close();
        fos.close();

    }

    public static void main(String[] args) throws Exception{
        copyFile();
    }
}
