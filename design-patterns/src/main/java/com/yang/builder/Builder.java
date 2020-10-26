package com.yang.builder;

abstract class Builder {
    //创建产品对象
    protected Iphone iphone = new Iphone();
    public abstract void buildBattery();
    public abstract void buildScreen();
    //返回产品
    public Iphone getResult(){
        return iphone;
    }
}
