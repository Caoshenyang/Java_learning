package com.yang.jdk8learning.java8_demo_01;


import java.time.*;
import java.time.temporal.ChronoUnit;

class Java8Test08 {

    public static void main(String[] args) {
        Duration d1 = Duration.between(LocalDateTime.of(2020, 9, 1, 15, 55, 55, 888), LocalDateTime.now());
        Duration d2 = Duration.between(LocalTime.of(17, 55, 10), LocalTime.now());
        Duration d3 = Duration.between(Instant.ofEpochMilli(1599037854143L), Instant.now());
        System.out.println(d3.toMinutes());

        //Duration对象用秒和纳秒来衡量时间的长短，所以入参不能使用LocalDate类型, 否则抛UnsupportedTemporalTypeException: Unsupported unit: Seconds
        //Duration.between(LocalDate.of(2019, 10, 7), LocalDate.now());

        //如果想要对多个时间对象进行日期运算，可以用Period
        Period p1 = Period.between(LocalDate.of(2019, 1, 1), LocalDate.now());
        System.out.println(p1.getYears() + "\t" + p1.getMonths() + "\t" + p1.getDays());

    }
}