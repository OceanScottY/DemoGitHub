package TimerDemo;

import Utils.DateUtil;

import java.util.TimerTask;

/**
 * Created by Scott on 2018/10/22
 */
public class TimerTaskDemo extends TimerTask {


    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.getCurrentTime(DateUtil.BB_TIME_PATTERN));
    }
}
