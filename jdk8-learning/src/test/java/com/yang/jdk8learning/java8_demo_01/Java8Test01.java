package com.yang.jdk8learning.java8_demo_01;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

class Java8Test01 {

    @Test
    public static void main(String[] args) {

        //匿名内部类
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };

        //Lambda 表达式
        Runnable r2 = () -> System.out.println("Hello World!");

        r.run();
        r2.run();

        //使用匿名内部类作为参数传递
        TreeSet<String> t = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });

        //Lambda 表达式作为参数传递
        TreeSet<String> t2 = new TreeSet<>((o1, o2) -> {return Integer.compare(o1.length(), o2.length());});

        //System.out.println(comparator);

    }
}