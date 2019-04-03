package JacksonParse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Scott on 2018/6/3
 *
 * 使用Jackson实现Json与string的相互转化
 */
public class JsonUtil {
    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 将 json字符串解析为List<Entry>
     * @param bytes
     *              byte[]
     * @return
     *              List<Entry>
     */
    public static List<Entry> getEntriesDemo(byte[] bytes) {
        List<Entry> entries = new ArrayList<>();
        if (bytes == null || bytes.length == 0) {
            return entries;
        }
        try {
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(bytes);
            JsonToken jt;
            String name, value;

            //JsonToken.START_OBJECT = "{"  ,JsonToken.END_OBJECT = "}"
            while ((jt = jp.nextToken()) != JsonToken.END_OBJECT) {
                if (jt == JsonToken.START_OBJECT) {
                    continue;
                }
//                if(jt == JsonToken.FIELD_NAME){
                    System.out.println(jt + "  name:" + jp.getCurrentName() + "   text:" + jp.getText() + "  " + jp);
//                }

            }
        } catch (Exception e) {
            //log
            logger.error("SerializeUtil getEntries failed.",e);
        }
        return entries;
    }

    public static List<Entry> getEntries(byte[] bytes) {
        List<Entry> entries = new ArrayList<>();
        if (bytes == null || bytes.length == 0) {
            return entries;
        }
        try {
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(bytes);
            JsonToken jt;
            String name, value;
            while ((jt = jp.nextToken()) != JsonToken.END_OBJECT) {
                if (jt == JsonToken.START_OBJECT) {
                    continue;
                }
                if (jt == JsonToken.FIELD_NAME) {
                    name = jp.getCurrentName();
                    jp.nextToken();
                    value = jp.getText();
                    Entry e = new Entry(name, value);
                    entries.add(e);
                }
            }
        } catch (Exception e) {
            //log
            logger.error("SerializeUtil getEntries failed.",e);
        }
        return entries;
    }


    public static String getStringFromEntries(List<Entry> entries) {
        if (entries.size() == 0) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        Iterator<Entry> i = entries.iterator();
        while (i.hasNext()) {
            Entry entry = i.next();
            sb.append("\"");
            sb.append(entry.key);
            sb.append("\"");
            sb.append(":");
            sb.append("\"");
            sb.append(entry.value);
            sb.append("\"");
            if (i.hasNext()) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    public static void main(String[] args) {
        List<Entry> es = new ArrayList<>();
        Entry e0 = new Entry("type","21");
        Entry e1 = new Entry("detail","{\"error\":\"3\"}");
        Entry e2 = new Entry("id","1");
/*        Entry e3 = new Entry("file_name","home");
        Entry e4 = new Entry("errorType","1");
        Entry e5 = new Entry("errorCode","0");
        Entry e6 = new Entry("repair","0");
        Entry e7 = new Entry("repair_fs_id","1");
        Entry e8 = new Entry("repair_file","");*/
        es.add(e0);
        es.add(e1);
        es.add(e2);
//        es.add(e3);
//        es.add(e4);
//        es.add(e5);
//        es.add(e6);
//        es.add(e7);
//        es.add(e8);

        String string = getStringFromEntries(es);
        System.out.println(string);

        List<Entry> res = getEntriesDemo(string.getBytes());
        /*for(Entry temp : res){
            System.out.println(" key: " + temp.getKey() + "  value:" + temp.getValue());
        }*/
        String de = "fsdfdsfdasds \n";
        System.out.println(de);
        String te = de.replaceAll("\n","");
        System.out.println(te);
        System.out.println("sdfsdf");
    }
}
