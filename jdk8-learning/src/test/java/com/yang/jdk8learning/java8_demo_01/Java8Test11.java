package com.yang.jdk8learning.java8_demo_01;


import java.time.*;
import java.util.Set;

class Java8Test11 {

    public static void main(String[] args) {
        //获取所有合法的“区域/城市”字符串
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        //availableZoneIds.forEach(System.out::println);
        //获取系统默认时区
        ZoneId systemZoneId = ZoneId.systemDefault();
        System.out.println("当期时区: " + systemZoneId);

        // 获取当前时间日期
        ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
        System.out.println("date1: " + date1);

        //创建时区
        ZoneId id = ZoneId.of("Europe/Paris");
        System.out.println("ZoneId: " + id);

        //LocalDate、LocalDateTime、Instant 转 ZonedDateTime
        ZonedDateTime zdt1 = LocalDate.of(2020, 9, 3).atStartOfDay(ZoneId.systemDefault());
        ZonedDateTime zdt2 = LocalDateTime.of(2020, 9, 3, 14, 10, 55, 888).atZone(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zdt3 = Instant.now().atZone(ZoneId.of("Asia/Yerevan"));

        //Instant转LocalDateTime
        LocalDateTime ldt1 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
}