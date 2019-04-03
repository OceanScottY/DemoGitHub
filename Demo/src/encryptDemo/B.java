package encryptDemo;

import java.util.Arrays;

/**
 * @文件描述：
 * @创建者：
 * @创建日期：2019/2/25
 * @版权声明：
 * @缩进/编码：tabstop=4 utf-8
 */
public class B implements A {

    public int age;
    public String name;
    public byte[] test;

    public B() {
    }

    public B(int age, String name, byte[] test) {
        this.age = age;
        this.name = name;
        this.test = test;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getTest() {
        return test;
    }

    public void setTest(byte[] test) {
        this.test = test;
    }

    @Override
    public void show() {
        System.out.println("this is B");
    }


    public static void main(String[] args) {
        A a = new B();
        a.show();
    }

    @Override
    public String toString() {
        return "B{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", test=" + Arrays.toString(test) +
                '}';
    }
}
