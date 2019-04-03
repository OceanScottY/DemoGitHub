package log4jDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        Logger logger1 = LoggerFactory.getLogger("operation");
        logger1.debug("你好   我是一个新的文件");
        Logger logger2 = LoggerFactory.getLogger(Demo.class);

        String str = " I am a boy!";

        try {
            String newStr = str.substring(str.length()+2);
            System.out.println(newStr);
        } catch (Exception e) {
            logger1.info("异常",e);
            logger2.error("这是root logger的异常",e);
        }
//        String str = String.format("INSERT INTO fs ( id, name, type, mdpool, datapools, mountaddr, mountport, keyring, fsconf, fssize)" +
//                        "VALUES ('%d', '%s', '%s','%s', '%s', '%s', '%s', '%s', '%s', %s)",5, "test", "test",null,
//                null, null, null, null, null, null);
//        System.out.println(str);


        /*File file = new File("yuhaiyang");
        if(!file.exists()){
            try {
                file.mkdir();
                System.out.println("完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        /*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        c.add(Calendar.MONTH,1);
        String str = df.format(c.getTime());
        System.out.println(str);*/



    }
}
