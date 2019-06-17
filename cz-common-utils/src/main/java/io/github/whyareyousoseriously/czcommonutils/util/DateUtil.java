package io.github.whyareyousoseriously.czcommonutils.util;

import java.text.SimpleDateFormat;
import java.util.Date;

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


}
