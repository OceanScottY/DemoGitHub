package callBack;

/**
 * Created by Scott on 2018/10/12
 */
public class ClassA  {

    private MyInterface myInterface;

    public void setMyInterface(MyInterface myInterface) {
        this.myInterface = myInterface;
    }

    public void call(){
        System.out.println("this is ClassA");
        myInterface.callBack();
    }

}
