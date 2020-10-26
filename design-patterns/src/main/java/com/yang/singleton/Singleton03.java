package com.yang.singleton;

public class Singleton03 {

    private Singleton03() {
    }

    public static Singleton03 getInstance() {
        return InnerSingleton.INSTANCE;
    }

    private static class InnerSingleton {
        private static final Singleton03 INSTANCE = new Singleton03();
    }

}

