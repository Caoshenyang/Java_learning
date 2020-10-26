package com.yang.passbyvalue;

import java.util.Arrays;


/**
 * 基本数据类型传的是克隆的一个值，
 * 引用类型传的是克隆指向对象地址值的一个值
 */
public class Demo {

    public static void change(int a) {
        a = 2;
        System.out.println("我是方法里的a：" + a);
    }

    public static void changArray(int[] data) {
        data[0] = 3;
        data[1] = 4;
        System.out.println("我是方法里的data：" + Arrays.toString(data));
    }
}

class Test {
    public static void main(String[] args) {
        //基本数据类型
        int a = 0;
        //引用数据类型
        int[] data = {1, 2};

        System.out.println("我是初始化的a：" + a);
        System.out.println("我是初始化的data：" + Arrays.toString(data));

        Demo.change(a);
        Demo.changArray(data);
        System.out.println("我是change后的a：" + a);
        System.out.println("我是change后的data：" + Arrays.toString(data));

    }
}