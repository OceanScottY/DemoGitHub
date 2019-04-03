package JacksonParse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Scott on 2018/6/3
 */
public class UserDelReq {

    String type;
    int[] id;

    public UserDelReq() {
    }

    public UserDelReq(String type, int[] id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[] getId() {
        return id;
    }

    public void setId(int[] id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", id=" + Arrays.toString(id) +
                '}';
    }

    public List<Entry> getList(){
        List<Entry> list = new ArrayList<>();
        String typeValue = this.type;
        String idValue = Arrays.toString(id);
        Entry typeEn = new Entry("type",typeValue);
        Entry idEn = new Entry("id",idValue);
        list.add(typeEn);
        list.add(idEn);
        return list;
    }
}
