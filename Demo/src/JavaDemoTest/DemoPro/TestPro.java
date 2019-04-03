package JavaDemoTest.DemoPro;

/**
 * Created by Scott on 2019/2/18
 */
public class TestPro {

    private String name;
    private String wxn;

    public TestPro() {
        super();
    }

    public TestPro(String name, String wxn) {
        this.name = name;
        this.wxn = wxn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWxn() {
        return wxn;
    }

    public void setWxn(String wxn) {
        this.wxn = wxn;
    }

    @Override
    public String toString() {
        return "TestPro{" +
                "name='" + name + '\'' +
                ", wxn='" + wxn + '\'' +
                '}';
    }
}
