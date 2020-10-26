package com.yang.jdk8learning.java8_demo_01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;

class Java8Test06 implements TestInterface01,TestInterface02 {

    public static void main(String[] args) {
        /**
         * LocalDate
         *
         * getYear()        当前日期年份信息
         * getMonth()       当前日期月份信息
         * getDayOfMonth()  当前日期是一个月中的第几天
         * getDayOfWeek()   当前日期是周几
         * lengthOfMonth()  当前月有多少天
         * isLeapYear       是否是闰年
         */
        System.out.println("******************* LocalDate *******************");
        LocalDate localDate = LocalDate.of(2020, 9, 1);
        System.out.println(localDate.getYear() + "\t" + localDate.getMonth() + "\t" + localDate.getDayOfMonth() + "\t" + localDate.getDayOfWeek() + "\t" + localDate.lengthOfMonth() + "\t" + localDate.isLeapYear());
        LocalDate now = LocalDate.now();
        System.out.println(now.get(ChronoField.YEAR) + "\t" + now.get(ChronoField.MONTH_OF_YEAR) + "\t" + now.get(ChronoField.DAY_OF_MONTH));

        /**
         * LocalTime
         *
         * getHour      时
         * getMinute    分
         * getSecond    秒
         */
        System.out.println("******************* LocalTime *******************");
        LocalTime localTime = LocalTime.of(20, 44, 12);
        System.out.println(localTime.getHour() + "\t" + localTime.getMinute() + "\t" + localTime.getSecond());

        /**
         * 解析字符串
         * 默认格式: yyyy-MM-dd
         */
        System.out.println("******************* 解析字符串 *******************");
        LocalDate localDate2 = LocalDate.parse("2020-09-01");
        System.out.println(localDate2.toString());

        /**
         * 解析字符串
         * 默认格式: HH:mm:ss.SSS
         */
        LocalTime localTime2 = LocalTime.parse("20:42:12.828");
        System.out.println(localTime2.toString());

        /**
         * 互相进行类型转换
         */
        System.out.println("******************* 互相进行类型转换 *******************");

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 9, 1, 16, 12, 10, 888).atZone(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        System.out.println(localDateTime1);

        //LocalDate + LocalTime -> LocalDateTime
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate2, localTime2);
        System.out.println(localDateTime2);

        //组合拼接
        LocalDateTime localDateTime3 = localDate2.atTime(10, 10, 10);
        System.out.println(localDateTime3);

        LocalDateTime localDateTime4 = localDate2.atTime(localTime2);
        System.out.println(localDateTime4);

        LocalDateTime localDateTime5 = localTime2.atDate(localDate2);
        System.out.println(localDateTime5);

        LocalDateTime localDateTime6 = LocalDateTime.parse("2020/09/01 16:19:20.888", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
        System.out.println(localDateTime6);
        LocalDate localDate3 = localDateTime6.toLocalDate();
        System.out.println(localDate3);
        LocalTime toLocalTime3 = localDateTime6.toLocalTime();
        System.out.println(toLocalTime3);
    }
}