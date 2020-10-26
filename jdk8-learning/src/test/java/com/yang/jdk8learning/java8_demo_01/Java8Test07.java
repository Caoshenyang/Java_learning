package com.yang.jdk8learning.java8_demo_01;


import java.time.Instant;

class Java8Test07 {

    public static void main(String[] args) {
        //获取当前时间戳
        long milli = Instant.now().toEpochMilli();
        System.out.println("milli：" + milli);
        //根据某个时间戳获取Instant实例
        Instant instant = Instant.ofEpochMilli(milli);
        System.out.println("instant：" + instant);

        //minusSeconds() 减一秒
        Instant instant2 = instant.minusSeconds(1L);
        System.out.println("instant2：" + instant2);

        //isBefore()和isAfter()比较大小
        System.out.println(instant.isAfter(instant2));
    }
}