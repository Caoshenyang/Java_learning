package com.yang.builder;

//具体建造者：实现了抽象建造者接口。
public class BuilderImpl extends Builder {
    @Override
    public void buildBattery() {
        iphone.setBattery(new Battery());
    }

    @Override
    public void buildScreen() {
        iphone.setScreen(new Screen());
    }
}
