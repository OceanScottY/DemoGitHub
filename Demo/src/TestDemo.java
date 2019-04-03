import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Scott on 2018/6/17
 */
public class TestDemo {

    public static void main(String[] args) {
        int index = new Random().nextInt(3);
        System.out.println(index);

        ArrayList<String> list = new ArrayList<>();
        list.add("你猜");
        list.add(1,"hello，如来佛");

    }


}
