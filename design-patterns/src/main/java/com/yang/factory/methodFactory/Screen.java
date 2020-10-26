package com.yang.factory.methodFactory;

//具体产品：屏幕
public class Screen implements IphoneInterface {
    @Override
    public void print() {
        System.out.println("显示屏生产完成");
    }
}
