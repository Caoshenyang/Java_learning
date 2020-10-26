package com.yang.jdk8learning.java8_demo_01;


import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

class Java8Test09 {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 本年本月最后一天
        System.out.println(localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
        // 本年本月第一天
        System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfMonth()));
        // 本年下一月第一天
        System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfNextMonth()));
        // 下一年第一天
        System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfNextYear()));
        // 本年最后一天
        System.out.println(localDateTime.with(TemporalAdjusters.lastDayOfYear()));
        // 下一个周五
        System.out.println(localDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
        // 本月第一个周五
        System.out.println(localDateTime.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)));
        // 本月最后一个周五
        System.out.println(localDateTime.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
        // 下一个周五，如果当前是周五则返回当前时间
        System.out.println(localDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)));
        // 前一个周五
        System.out.println(localDateTime.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)));
        // 前一个周五，如果当前是周五则返回当前时间
        System.out.println(localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)));
    }
}