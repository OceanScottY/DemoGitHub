package JavaDemoTest.ConcurrentDemo;

import org.apache.poi.ss.formula.functions.Count;

import java.util.concurrent.CountDownLatch;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/30
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class TestHarness {

    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i=0; i<nThreads; i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try{
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException e){

                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;

    }

    public static void main(String[] args) throws Exception{
        System.out.println(timeTasks(3,new ThreadDemo()));
    }

}
