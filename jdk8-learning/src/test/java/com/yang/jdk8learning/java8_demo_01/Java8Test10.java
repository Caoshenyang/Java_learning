package com.yang.jdk8learning.java8_demo_01;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Java8Test10 {

    public static void main(String[] args) {
        //日期转字符串
        LocalDate ld = LocalDate.of(2020, 9, 1);
        String s1 = ld.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(s1);
        String s2 = ld.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s2);
        //字符串转日期
        LocalDateTime ld1 = LocalDateTime.parse("2020-09-01 18:00:00.888", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        System.out.println(ld1);
    }
}