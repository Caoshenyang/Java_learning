package com.yang.statictest;

public class Demo {
    static int a;
    static String string = print();

    static {
        System.out.println("啊啊啊啊啊");
    }

    public static String print(){
        a++;
        System.out.println("初始化变量");
        return "aaa";
    }
}
