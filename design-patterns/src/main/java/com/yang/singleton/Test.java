package com.yang.singleton;

class Test {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + CASSingleton.getInstance());
        }, "线程1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + CASSingleton.getInstance());
        }, "线程2").start();
    }
}
