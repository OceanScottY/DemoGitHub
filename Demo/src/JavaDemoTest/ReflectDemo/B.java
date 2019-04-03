package JavaDemoTest.ReflectDemo;

/**
 * Created by Scott on 2019/2/21
 */
public class B extends A {

    public int ageNew;

    public B(int age, int ageNew) {
        super(age);
        this.ageNew = ageNew;
    }

    public int getAgeNew() {
        return ageNew;
    }

    public void setAgeNew(int ageNew) {
        this.ageNew = ageNew;
    }
}
