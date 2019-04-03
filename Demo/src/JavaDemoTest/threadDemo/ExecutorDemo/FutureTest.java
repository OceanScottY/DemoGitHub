package JavaDemoTest.threadDemo.ExecutorDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/4/2
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class FutureTest {

    public static class TaskRun implements Runnable{
        @Override
        public void run() {
            System.out.println("ah");
        }
    }

    public static class TaskCall implements Callable<String>{
        @Override
        public String call() {
            System.out.println("call");
            return "this is a call test";
        }
    }

    public static void main(String[] args) throws Exception{
        /*ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new TaskRun());
        }*/

        List<Future<String>> results = new ArrayList<>();
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            results.add(es.submit(new TaskCall()));
        }



    }
}
