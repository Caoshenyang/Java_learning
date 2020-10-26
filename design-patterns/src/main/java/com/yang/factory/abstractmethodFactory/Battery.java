package com.yang.factory.abstractmethodFactory;

public class Battery implements IphoneInterface {

    @Override
    public void print() {
        System.out.println("电池生产完成");
    }
}
