package com.yang.jdk8learning.java8_demo_01;


import java.util.UUID;

class Java8Test12 {

    public static void main(String[] args) {
        String name = "dsasd.jpg";
        String substring = name.substring(name.lastIndexOf("."));
        System.out.println(substring);
    }
}