package io.github.whyareyousoseriously.czcommonutils.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.fasterxml.jackson.databind.util.ISO8601Utils.format;

/**
 * @author chenzhen
 * Created by chenzhen on 2019/2/21.
 */
public class DateUtil {

    /***
     * 将时间转换为时间戳
     * @param date
     * @return
     */
    public static long dateToStamp(Date date){
        return date.getTime();
    }


    /**
     * 将时间戳转换为时间
     * @param s
     * @return
     */
    public static String stampToDate(long s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        return simpleDateFormat.format(date);
    }

    /**
     * 获取指定时间戳今天的零点时间戳
     * @param s 指定时间戳
     * @return 零点时间戳
     */
    public static String zeroPoint(String s){
        Long currentTimestamps = System.currentTimeMillis();
        Long oneDayTimestamps = (long) (60 * 60 * 24 * 1000);
        return String.valueOf(currentTimestamps-(currentTimestamps+60*60*8*1000)%oneDayTimestamps);
    }


    /**
     * 获取日期年份
     * @param date 日期
     * @return
     */
    public static String getYear(Date date) {
        return format(date).substring(0, 4);
    }
    /**
     * 功能描述：返回月
     *
     * @param date
     *            Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 功能描述：返回日期
     *
     * @param date
     *            Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 功能描述：返回小时
     *
     * @param date
     *            日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 功能描述：返回分
     *
     * @param date
     *            日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date
     *            Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 功能描述：返回毫
     *
     * @param date
     *            日期
     * @return 返回毫
     */
    public static long getMillis(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }
}
