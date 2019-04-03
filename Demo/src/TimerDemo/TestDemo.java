package TimerDemo;

import Utils.DateUtil;

import java.util.Timer;

/**
 * Created by Scott on 2018/10/22
 */
public class TestDemo {

    public static void main(String[] args) {
        System.out.println(DateUtil.getCurrentTime(DateUtil.BB_TIME_PATTERN));
        Timer timer = new Timer();
        TimerTaskDemo myTask = new TimerTaskDemo();
        timer.schedule(myTask,1000,2000);

    }

}
