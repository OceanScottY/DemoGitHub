package JavaDemoTest.IODemo.FileDemo;

import java.io.File;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Demo1 {

    public static void main(String[] args) throws Exception{
        String path = "./test";
        File file = new File(path + "/A");
        File fileNew = new File(path + "/A/test.txt");
        System.out.println(file.delete());
    }
}
