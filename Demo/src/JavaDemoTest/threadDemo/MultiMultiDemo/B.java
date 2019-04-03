package JavaDemoTest.threadDemo.MultiMultiDemo;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/19
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class B implements Runnable {
    @Override
    public void run() {
        System.out.println("   我要通知他继续执行程序......" + Thread.currentThread().getName());
        A.signalSend();
    }
}
