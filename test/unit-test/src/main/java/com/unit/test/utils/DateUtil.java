package com.unit.test.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DateUtil {
    private static final String DEFAULT_YMD = "yyyy-MM-dd";

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转换为Date类型日期
     *
     * @param date   日期字符串
     * @param format 日期字符串的现有格式
     * @return 日期
     * @throws ParseException 异常
     */
    public static Date formatDate(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    /**
     * 日期格式转化
     */
    public static String dateFormat(String srcDate, String srcFormat, String destFormat) {
        if (srcFormat == null || "".equals(srcFormat)) {
            srcFormat = "yyyy-MM-dd";
        }
        if (destFormat == null || "".equals(destFormat)) {
            destFormat = "yyyy-MM-dd";
        }
        if (srcDate == null || "".equals(srcDate)) {
            return "";
        }

        Calendar cal = Calendar.getInstance();
        Date currentTime;
        try {
            currentTime = new SimpleDateFormat(srcFormat).parse(srcDate);
        } catch (ParseException e) {
            currentTime = new Date();
        }
        cal.setTime(currentTime);
        return new SimpleDateFormat(destFormat).format(cal.getTime());
    }

    /**
     * 计算两个日期相差天数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 相差天数
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                // 闰年
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {
                    timeDistance += 366;
                } else {
                    // 不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            log.info("判断day2 - day1 = {} ", (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 计算两个日期相差天数
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return 相差天数
     */
    public static int differentDay(Date date1, Date date2) {
        long dateTimeOne = date1.getTime();
        long dateTimeTwo = date2.getTime();
        return Math.abs((int) ((dateTimeOne - dateTimeTwo) / (24 * 60 * 60 * 1000)));
    }
}
