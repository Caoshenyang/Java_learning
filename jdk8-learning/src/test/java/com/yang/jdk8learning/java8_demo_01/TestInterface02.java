package com.yang.jdk8learning.java8_demo_01;

public interface TestInterface02 {
    default void print2() {
        System.out.println("我TestInterface02是默认方法。");
    }
}
