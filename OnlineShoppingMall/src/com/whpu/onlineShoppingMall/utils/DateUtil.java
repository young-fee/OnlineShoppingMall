package com.whpu.onlineShoppingMall.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_MI_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss"; 
    public static final String DATETIME_FORMAT_00 = "yyyy-MM-dd 00:00:00"; 
    public static final String DATETIME_FORMAT_24 = "yyyy-MM-dd 24:60:60"; 
    public static final String CH_DATE_FORMAT_YMD = "yyyy年MM月dd日";
    public static final String CH_DATE_FORMAT_MD = "MM月dd日";
    public static final String DATE_DI_FORMAT = "YYYYMMDDhhmmss";
    public static final long DAY_MILLI = 24 * 60 * 60 * 1000; // 一天的MilliSecond

    /**
     * 时间戳转字符串
     * 
     * @param time
     * @param format
     * @return
     */
    public static String timestampToString(Timestamp time, String format) {
        DateFormat sdf = new SimpleDateFormat(format);

        return sdf.format(time);
    }

    public static Date strToDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

            return sdf.parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static Date strToDate(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            return sdf.parse(dateStr);
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateToStr(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);

            return sdf.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateToStr(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);

            return sdf.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取Date的小时数
     * 
     * @param date
     * @return
     */
    public static Integer getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取Date的分钟数
     * 
     * @param date
     * @return
     */
    public static Integer getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 取得两个日期之间的日数
     * 
     * @return t1到t2间的日数，如果t2 在 t1之后，返回正数，否则返回负数
     */
    public static long daysBetween(java.util.Date t1, java.util.Date t2) {
        return (t2.getTime() - t1.getTime()) / DAY_MILLI;
    }
    /**
     * 当前时间的字符串
     * 
     * @param date
     *            时间
     * @return 时间的数字字符串格式
     */
    public static String mathString(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.UK);
        return time.format(date);
    }
    
    
   public static void main(String[] args) throws ParseException {
	   Date date = new Date();
	   SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String day =  time.format(date);
	   Date day2 = time.parse("2019-04-24 00:00:00");
	   System.out.println(day);
	   System.out.println(day2);
   }
}
