package JavaDemoTest.ReflectDemo;

/**
 * Created by Scott on 2019/2/21
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + classLoader.toString());

        ClassLoader extensionClassLoader = classLoader.getParent();
        System.out.println("扩展类加载器：" + extensionClassLoader);

        try{
            classLoader.loadClass("JavaDemoTest.ReflectDemo.Demo1");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
