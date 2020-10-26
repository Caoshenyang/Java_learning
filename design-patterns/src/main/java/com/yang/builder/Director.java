package com.yang.builder;

public class Director {
    private final Builder builder;
    public Director(Builder builder){
        this.builder = builder;
    }
    //产品组装方法
    public Iphone construct(){
        builder.buildBattery();
        builder.buildScreen();
        return builder.getResult();
    }
}
