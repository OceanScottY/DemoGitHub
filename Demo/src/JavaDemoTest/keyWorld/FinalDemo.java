package JavaDemoTest.keyWorld;

import java.util.*;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/6
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class FinalDemo {


    public static void main(String[] args) {
        final Map<String,String> test = new HashMap<>();
        test.put("1","1");
        test.put("2","2");
        test.put("3","3");
        test.put("4","4");
        test.put("1","改变了");


        System.out.println(test.toString());

        String s = "test";
        System.out.println(s.equals("q"));
        HashSet<String> set = new HashSet<>();
        set.add("dsfs");
        HashMap<String,String> map = new HashMap<>();
        map.put("","");

    }
}
