package com.yang.passbyvalue;

public class StringDemo {
    public static void change(String s) {
        s = "aaa";
        System.out.println("我是方法里的s：" + s);
    }
}

class StringDemoTest {
    public static void main(String[] args) {
        String s = "a";

        System.out.println("我是初始化的s：" + s);
        StringDemo.change(s);
        System.out.println("我是修改后的s：" + s);

    }
}