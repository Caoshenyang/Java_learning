package com.yang.jdk8learning.java8_demo_01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

class Java8Test03 {
    public static void main(String args[]) {
        //使用Lambda表达式
        Arrays.asList("张三1", "李四1", "王二1").stream().forEach((x) -> Java8Test03.show(x));
        //使用静态方法引用
        Arrays.asList("张三2", "李四2", "王二2").stream().forEach(Java8Test03::show);
    }

    public static void show(String name) {
        System.out.println(name + "show!");
    }
}