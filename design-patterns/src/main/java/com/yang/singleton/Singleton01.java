package com.yang.singleton;

public class Singleton01 {
    //自行创建，用静态变量保存
    private static Singleton01 instance = new Singleton01();
    //构造器私有化
    private Singleton01() {
    }
    //向外提供获取方法
    public static Singleton01 getInstance() {
        return instance;
    }
}

