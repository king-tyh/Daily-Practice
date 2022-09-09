package com.unit.test.utils;

import java.text.ParseException;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    @Test
    void test_differentDays_date2IsGreater() throws ParseException {
        Date date1 = DateUtil.formatDate("2022-08-31", "yyyy-MM-dd");
        Date date2 = DateUtil.formatDate("2022-09-06", "yyyy-MM-dd");
        int diff = DateUtil.differentDays(date1, date2);
        Assertions.assertEquals(6, diff);
    }

    @Test
    void test_differentDays_WhenAcrossTheYears() throws ParseException {
        Date date1 = DateUtil.formatDate("2022-08-31 08:05:19", "yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtil.formatDate("1996-08-31 22:55:19", "yyyy-MM-dd HH:mm:ss");
        int diff = DateUtil.differentDays(date1, date2);
        Assertions.assertEquals(1, diff);
    }

    @Test
    void test_differentDay_date1IsGreater() throws ParseException {
        Date date1 = DateUtil.formatDate("2022-09-06", "yyyy-MM-dd");
        Date date2 = DateUtil.formatDate("2022-08-31", "yyyy-MM-dd");
        int diff = DateUtil.differentDay(date1, date2);
        Assertions.assertEquals(6, diff);
    }

    @Test
    void test_differentDay_date2IsGreater() throws ParseException {
        Date date1 = DateUtil.formatDate("2022-08-31", "yyyy-MM-dd");
        Date date2 = DateUtil.formatDate("2022-09-06", "yyyy-MM-dd");
        int diff = DateUtil.differentDay(date1, date2);
        Assertions.assertEquals(6, diff);
    }

    @Test
    void test_differentDay_WhenAcrossTheYears() throws ParseException {
        Date date1 = DateUtil.formatDate("2022-08-31 08:05:19", "yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtil.formatDate("1996-08-31 22:55:19", "yyyy-MM-dd HH:mm:ss");
        long diff = DateUtil.differentDay(date1, date2);
        Assertions.assertEquals(9495, diff);
    }

    @Test
    void test_differentDay_5() throws ParseException {
        Date date1 = DateUtil.formatDate("2022-08-31 08:05:19", "yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtil.formatDate("2022-07-31 07:55:19", "yyyy-MM-dd HH:mm:ss");
        long diff = DateUtil.differentDay(date1, date2);
        Assertions.assertEquals(31, diff);
    }
}