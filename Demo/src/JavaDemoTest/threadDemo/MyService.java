package JavaDemoTest.threadDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @文件描述： 线程间等待通知
 * @创建者：
 * @创建日期： 2019/3/6
 * @版权声明：
 * @缩进/编码： tabstop=4 utf-8
 */
public class MyService {

    public ReentrantLock lock = new ReentrantLock();
    public Condition condition = lock.newCondition();

    public void await(){
        try {
            lock.lock();
            System.out.println("等待的开始时间是： " + System.currentTimeMillis());
            condition.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void signal(){
        try{
            lock.lock();
            System.out.println("等待结束的时间是： " + System.currentTimeMillis());
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

}
