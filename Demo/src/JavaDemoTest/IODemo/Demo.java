package JavaDemoTest.IODemo;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/11
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Demo {


    public static void main(String[] args) {
            String[][] test = {
                    {"你","猜","猜"},
                    {"我","是","谁","?"}
            };
        System.out.println(test.length);
            for(int i=0; i<test.length; i++){
                System.out.println(test[i].length);
            }
    }
}
