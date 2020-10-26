package com.yang.builder;

public class Client {
    public static void main(String[] args) {
        Builder builder = new BuilderImpl();
        Director director = new Director(builder);
        Iphone iphone = director.construct();
        iphone.show();
    }
}
