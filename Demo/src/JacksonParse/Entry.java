package JacksonParse;

/**
 * Created by nick on 2017/10/23.
 */
/**
 * 序列化结果项，包含一个key值，一个value值，对应于Json格式中的键值（key:value）形式。
 */
public class Entry {
    public String key;
    public String value;

    public Entry() {}

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
        if (this.value == null) {
            this.value = "";
        }
    }

    public String toString() {
        return key + "::" + value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}