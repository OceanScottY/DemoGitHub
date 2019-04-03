package JavaDemoTest.IODemo.StreamDemo;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @文件描述： FileInputStream 操作文件   文件的结束标记是-1，这个-1是int类型的
 * @创建者：
 * @创建日期：2019/3/12
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class FileInputStreamDemo {


    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("abc.txt");
        int x = 0;
        while((x = fileInputStream.read()) != -1){
            System.out.print((char)x);
        }

        fileInputStream.close();
    }
}
