package NettyTest;

import NettyTest.ClientTest.NettyClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Scott on 2018/9/20
 */
public class test {
    final static Object object = new Object();
    public static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "  start,");
                try{
                    object.wait();
                    System.out.println(Thread.currentThread().getName() + "  被唤醒了,");
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "  end,");
            }
        }
    }
    public static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + "  start,");
                object.notifyAll();
                System.out.println(Thread.currentThread().getName() + "  end,");
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showTest(){
        System.out.println("this is a test method in test class");
    }

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void test(){
        readWriteLock.writeLock().lock();
        try{
            System.out.println("获得写入锁");
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {

        /*T1 t1_1 = new T1();
        t1_1.setName("t1_1");
        T1 t1_2 = new T1();
        t1_2.setName("t1_2");
        T2 t2 = new T2();
        t2.setName("t2");

        t1_1.start();
        t1_2.start();
        t2.start();*/


        /*System.out.println(16 >> 4);
        HashMap<String,String> map = new HashMap<>();
        map.put("sdfas","asdfas");
        int a = 4>>1;
        System.out.println(a);

        readWriteLock.writeLock().lock();
        try{
            test();
        }finally {
            readWriteLock.writeLock().unlock();
        }*/
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("qwer");


    }

}
