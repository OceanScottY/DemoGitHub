package Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by Scott on 2018/5/24.
 *
 */
public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTREN = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_PATTREN_FULL = "yyyy-MM-dd HH:mm:ss.S";

    //此格式为报备模块返回给CM的日期格式
    public static final String BB_TIME_PATTERN = "EEE MMM d HH:mm:ss yyyy";

    /**
     * yyyyMMddHHmmss
     */
    public static final String TIME_PATTREN_COMPACTED = "yyyyMMddHHmmssS";
    public static final String TIME_PATTREN_SHORT = "MMddHHmmss";// MMDDhhmmss
    public static final String TIME_PATTREN_HHmmss = "HH:mm:ss";// MMDDhhmmss

    /**
     * 获取时间差字符串
     * @param time1
     *                  字符串形式的时间
     * @param pattern1
     *                  time1的时间格式
     * @param time2
     *                  字符串形式的时间
     * @param pattern2
     *                  time2的时间格式
     * @return
     *                  两个时间差
     */
    public static String getElapsedTimeStr(String time1, String pattern1,String time2, String pattern2){
        String result = "";
        SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
        SimpleDateFormat format2 = new SimpleDateFormat(pattern2);

        Date begin = null;
        Date end = null;
        long between = 0;
        try {
            if(compareTime(time1,pattern1,time2,pattern2)>=0){
                end = format1.parse(time1);
                begin = format2.parse(time2);
            }else{
                begin = format1.parse(time1);
                end = format2.parse(time2);
            }
            between = (end.getTime()-begin.getTime())/1000;
        } catch (ParseException e) {
            logger.error("Fail to parse string to date",e);
            return null;
        }
        long day = between/(24*3600);
        long hour = between%(24*60*60)/3600;
        long minute = between%3600/60;
        long second = between%60;

        if(day > 0){
            result = String.format("%d天%d小时%d分钟%d秒",day,hour,minute,second);
        }else if(hour > 0){
            result = String.format("%d小时%d分钟%d秒",hour,minute,second);
        }else if (minute > 0){
            result = String.format("%d分钟%d秒",minute,second);
        }else {
            result = String.format("%d秒",second);
        }
        return result;
    }

    /**
     *  获取时间差字符串
     * @param time
     *                  字符串形式的时间
     * @param pattern
     *                  time的时间格式
     * @return
     *          string   time与当前时间的时间差
     */
    public static String getElapsedTimeStr(String time, String pattern){
        String now = getCurrentTime(DateUtil.TIME_PATTREN);
        return getElapsedTimeStr(time,pattern,now,DateUtil.TIME_PATTREN);

    }

    /**
     * 根据pattern获取当前时间
     * @param pattern
     *                  时间格式
     * @return
     *                  String，当前时间
     */
    public static String getCurrentTime(String pattern){
        SimpleDateFormat df = new SimpleDateFormat(pattern,Locale.ENGLISH);
        return df.format(new Date());
    }


    /**
     * 比较时间
     * @param time1
     *                  字符串形式的时间
     * @param pattern1
     *                  time1的时间格式
     * @param time2
     *                  字符串形式的时间
     * @param pattern2
     *                  time2的时间格式
     * @return
     *          int value
     *                   若time1<time2，value<0
      *                    time1=time2，value=0
       *                   time1>time2，vlaue>0
     */
    public static int compareTime(String time1, String pattern1,String time2, String pattern2){

        String result = "";
        SimpleDateFormat format1 = new SimpleDateFormat(pattern1);
        SimpleDateFormat format2 = new SimpleDateFormat(pattern2);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(format1.parse(time1));
            c2.setTime(format2.parse(time2));
        } catch (ParseException e) {
            logger.error("Fail to parse string to date",e);
        }

        return c1.compareTo(c2);
    }

    /**
     *
     * @param date
     *                  Date对象
     * @param days
     *                  在date基础上加的天数
     * @return
     *          Date对象
     */
    public static Date addDays(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, days);
        return c.getTime();
    }

    /**
     * 获取时间字符串
     * @param date
     *                  日期
     * @param pattern
     *                  要生成的日期的类型
     * @return
     *                  pattern类型的日期字符串
     */
    public static String getDateStr(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        df.setLenient(false);
        return df.format(date);
    }

    public static Date getDate(String dateStr, String pattern) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            df.setLenient(false);
            return df.parse(dateStr);
        } catch (ParseException ex) {
            logger.error(ex.getMessage());
            return null;

        }
    }

    /**
     * 将Wed May 30 23:30:36 2018此格式的日期转化为2018-05-30 23:30:36格式
     * @param dateStr
     *                  日期字符串，形式："Wed May 30 23:30:36 2018"
     * @return
     *                  返回标准日期字符串 "2018-05-30 23:30:36"
     */
    public static String parsePJQtoTimeString(String dateStr) throws Exception{
        SimpleDateFormat df = new SimpleDateFormat(DateUtil.BB_TIME_PATTERN,Locale.ENGLISH);
        Date date = df.parse(dateStr);
        return DateUtil.getDateStr(date,TIME_PATTREN);
    }

    public static void main(String[] args) {
        /*int res = DateUtil.compareTime("2018-05-26 17:02:46",DateUtil.TIME_PATTREN,
                "2018-05-25 17:02:46",DateUtil.TIME_PATTREN);

        String rs = DateUtil.getElapsedTimeStr("2018-05-24 16:02:48",DateUtil.TIME_PATTREN);*/
        /*Date date = addDays(new Date(),2);
        SimpleDateFormat df = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy",Locale.ENGLISH);
        System.out.println(getCurrentTime(BB_TIME_PATTERN));
        String dateStr = "Sat Jun  2 20:06:15 2018";
        try {
            System.out.println(parsePJQtoTimeString(dateStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
       System.out.println(getCurrentTime("EEE MMM d HH:mm:ss yyyy"));*/

        //获取当前时间的毫秒值
//        System.out.println(System.currentTimeMillis());

        String time1 = "2018-05-26 17:02:46";
        String time2 = "2018-05-27 17:02:46";

        System.out.println(compareTime(time1, TIME_PATTREN, time2, TIME_PATTREN));

    }


}
