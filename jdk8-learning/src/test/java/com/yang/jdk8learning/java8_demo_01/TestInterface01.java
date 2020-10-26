package com.yang.jdk8learning.java8_demo_01;

public interface TestInterface01 {
    default void print() {
        System.out.println("我TestInterface01是默认方法。");
    }
}
