package com.ppfuns.jxyyzl.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Author:Administrator
 * Time:2017/12/29.14:43
 */

public class DateUtils {
    public static final String TIME_FORMAT = "HH:mm";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    private final static String[] weeks = new String[]{"日", "一", "二", "三", "四", "五", "六",};

    public static String getCurrentDateString(String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date(System.currentTimeMillis());
        return sdf.format(date);
    }

    public static Date string2date(String date, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.parse(date);
    }

    public static String getWeek(long time) {
        Date date = new Date(time);
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(date);
        return weeks[mCalendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static String time2string(long time, String DoctorFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(DoctorFormat, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date date = new Date(time);
        return sdf.format(date);
    }

    public static String date2string(Date date, String DoctorFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(DoctorFormat, Locale.CHINA);
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return sdf.format(date);
    }
}
