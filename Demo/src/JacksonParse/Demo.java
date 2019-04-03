package JacksonParse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Demo {

    public static void main(String[] args) throws Exception{

/*        String string1 = "{\"age\":26,\"email\":\"532164710@qq.com\"}";
//        String string1 = "{\"age\":26,\"email\":\"532164710@qq.com\",\"name\":\"小明\",\"temp\" :\"this is a test.\"}";
        System.out.println("解析后的结果是： " + parseJsonToObject(string1,User.class).toString());*/
        String str = "{\"age\":26,\"email\":\"532164710@qq.com\",\"name\":\"娃哈哈\"}";

        ObjectMapper mapper = new ObjectMapper();

        User user = mapper.readValue(str,User.class);
        System.out.println(user.toString());
//        Cat catDemo = new Cat();
//        catDemo.age = 2;
//        catDemo.name = "雪琪";
//        Dog[] dogs = new Dog[2];
//        for(int i=0; i<2; i++){
//            Dog dog = new Dog();
//            dog.age = 3;
//            dog.name = "哈士奇" + i;
//            dogs[i] = dog;
//        }

//        String req = "{\"name\":\"雪琪\", \"age\":2, \"dog\":[{\"name\":\"哈士奇0\", age:3},{\"name\":\"哈士奇1\",\"age\":3}]}";
//        System.out.println(req);
//        parseJsonToObject(req,Cat.class);
//        Cat cat = new Cat();
//        try {
////            obj = mapper.readValue(jsonString,User.class);
//            cat = mapper.readValue(res,Cat.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("cat is : " + cat.toString());

    }
    static class Cat{
//        public String name;
        public int age;
        public Dog[] dog;

        @Override
        public String toString() {
            return "{" +
//                    "\"name\":'" + name + '\'' +
                    ", \"age\":" + age +
                    ", \"dog\":" + Arrays.toString(dog) +
                    '}';
        }
    }

    static class Dog{
        public String name;
        public int age;

        @Override
        public String toString() {
            return "{" +
                    "name:'" + name + '\'' +
                    ", age:" + age +
                    '}';
        }
    }

    /**
     * 将pojo对象转化为Json字符串
     * @param user
     * @return
     */
    public static String objToJsonString(User user){

        ObjectMapper mapper = new ObjectMapper();

        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */

        String res = null;
        try {
            res = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 将List<Object>转化为Json字符串
     * @param users
     * @return
     * @throws Exception
     */
    public static String listToJsonString(List<User> users)throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(users);
    }


    public static Object parseJsonToObject(String jsonString, Class clazz){
        ObjectMapper mapper = new ObjectMapper();

        Object obj = new Object();
        try {
//            obj = mapper.readValue(jsonString,User.class);
            obj = mapper.readValue(jsonString,clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public static List<User> parseJsonsToList(String jsonString){
        ObjectMapper mapper = new ObjectMapper();

        List<User> list = new ArrayList<User>();
        try {
            list = mapper.readValue(jsonString,new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
