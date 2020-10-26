package com.yang.factory.methodFactory;

public class Battery implements IphoneInterface {

    @Override
    public void print() {
        System.out.println("电池生产完成");
    }
}
