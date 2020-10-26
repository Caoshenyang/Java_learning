package com.yang.statictest;

public class StaticTest {
    public static void main(String[] args) {
        int a = Demo.a;
        int b = Demo.a;
        Demo demo = new Demo();
        System.out.println(a);
        System.out.println(b);
        System.out.println( demo.a);

    }
}
