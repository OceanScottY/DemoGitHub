package JavaDemoTest.threadDemo;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/3/6
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class MyThreadDemo implements Runnable{

    private MyService myService ;

    public MyThreadDemo(MyService myService) {
        super();
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.await();
        System.out.println("我没有停下来");
    }

    public static void main(String[] args) throws Exception{
//        MyService service = new MyService();
//        new Thread(new MyThreadDemo(service)).start();
//        Thread.sleep(3000);
//        service.signal();
        int x = 8;
        int low = 1, high = x;
        int mid = (low + high)/2;
        while(true){
            if(Math.pow(mid,2) <= x && Math.pow(mid+1, 2) > x){
                System.out.println(mid);
                break;
            }
            if(Math.pow(mid, 2) == x){
                System.out.println(mid);
                break;
            }
            if(Math.pow(mid, 2) < x){
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
            mid = (high + low)/2;
        }

    }
}
