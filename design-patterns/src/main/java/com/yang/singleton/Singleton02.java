package com.yang.singleton;

/**
 *
 */
public class Singleton02 {

    private volatile static Singleton02 instance = null;

    private Singleton02() {
    }

    public static Singleton02 getInstance() {
        //判断是否需要阻塞
        if (instance == null) {
            synchronized (Singleton02.class) {
                //判断是否别的线程已经创建了实例
                if (instance == null) {
                    instance = new Singleton02();
                }
            }
        }
        return instance;
    }
}

