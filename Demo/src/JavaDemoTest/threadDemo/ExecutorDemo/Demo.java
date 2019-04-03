package JavaDemoTest.threadDemo.ExecutorDemo;

import java.util.concurrent.*;

/**
 * @文件描述： 线程池实例
 * @创建者：
 * @创建日期：2019/4/1
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class Demo {

    private static final int N_THREADS = 100;
    private static final Executor exec = Executors.newFixedThreadPool(N_THREADS);


    /**
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
     */
    public static void newCachedThreadPoolTest(){
        ExecutorService cacheedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try{
                Thread.sleep(index * 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            cacheedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前的index是：" + index);
                }
            });
        }
    }
    /**
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    public static void newFixedThreadPoolTest(){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("当前的index是：" + index);
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            });
        }
    }
    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     * 表示延迟3秒执行
     */
    public static void newScheduledThreadPoolTest(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }
    /**
     * 创建一个定长线程池，支持定时及周期性任务执行
     * 表示1秒后，每3秒执行一次
     */
    public static void newScheduledThreadPoolTest2(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 1,3, TimeUnit.SECONDS);
    }
    /**
     * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行
     */
    public static void newSingleThreadExecutorTest(){
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("当前的index是:"+index);
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    public static void main(String[] args) {
//        newCachedThreadPoolTest();
//        newFixedThreadPoolTest();
//        newScheduledThreadPoolTest();
//        newScheduledThreadPoolTest2();
//        newSingleThreadExecutorTest();

    }
}
