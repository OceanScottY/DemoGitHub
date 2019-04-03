package lockDemo.Demo1;

/**
 * Created by Scott on 2018/10/19
 */
public class MyThread extends Thread {
    private MyService myService;

    public MyThread(MyService myService) {
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }

    public static void main(String[] args) {
        double d = 0d;
        System.out.println(d);
    }

}
