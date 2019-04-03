package JavaDemoTest.ConcurrentDemo;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/30
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class ThreadDemo implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "  开始执行。");
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "  执行完成。");
    }
}
