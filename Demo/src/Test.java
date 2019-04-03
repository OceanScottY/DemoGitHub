

/**
 * Created by Scott on 2019/2/20
 */
public class Test {

    final static String a = System.currentTimeMillis() + "";

    public static void main(String[] args) {
//        int a = 3, b= 5;
//        b = a ^ b;
//        a = a ^ b;
//        b = a ^ b;
//        System.out.println("a = " + a + "  b = " + b);
//        System.out.println((2<<3));
        try{
            Thread.sleep(1000);
            System.out.println(System.currentTimeMillis());

            System.out.println(a);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}
