package JavaDemoTest.threadDemo.MultiMultiDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/19
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class A implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();
    //等待建立连接的condition
    public static Condition conditionConn = lock.newCondition();
    //    等待发送消息的condition
    public static Condition conditionSend = lock.newCondition();
    public volatile  static ThreadLocal<Condition> localCondition = new ThreadLocal<>();

    //连接建立线程等待
    public static void awaitConn(){
        try{
            lock.lock();
            conditionConn.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //连接建立线程通知
    public static void signalConn(){
        try{
            lock.lock();
            conditionConn.signal();
        }finally {
            lock.unlock();
        }
    }

    //消息发送线程等待
    public static void awaitSend(){
        try{
            lock.lock();
            conditionSend.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //消息发送线程通知
    public static void signalSend(){
        try{
            lock.lock();
            Thread.sleep(5000);
            conditionSend.signal();
        }catch(Exception e) {
            e.printStackTrace();
        }finally{
                lock.unlock();
            }
        }


    @Override
    public void run() {
        System.out.println("   我停止了:" + Thread.currentThread().getName());
        awaitSend();
        System.out.println("   我睡醒了:" + Thread.currentThread().getName());
    }

    public static void main(String[] args)  throws Exception{
        for(int i=0; i<10; i++){
            Thread.sleep(1000);
            new Thread(new A()).start();
            new Thread(new B()).start();
        }
    }
}
