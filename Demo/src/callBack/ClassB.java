package callBack;

/**
 * Created by Scott on 2018/10/12
 */
public class ClassB implements MyInterface {

    public void callBack(){
        System.out.println("调用了ClassB的callBack方法");
    }


    public static void main(String[] args) {
        ClassA a = new ClassA();
//        a.setMyInterface(new ClassB());
        a.setMyInterface(new MyInterface() {
            @Override
            public void callBack() {
                System.out.println("this is a classBack method od a anonymous class ");
            }
        });
        a.call();
    }
}
