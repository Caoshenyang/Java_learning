package com.yang.jdk8learning.java8_demo_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class Java8Test04 {
    public static void main(String args[]) {
        //使用Lambda表达式
        Function<Integer, Integer[]> fun = (n) -> new Integer[n];
        //使用构造器引用
        Function<Integer, Integer[]> fun2 = Integer[]::new;
    }
}