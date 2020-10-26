package com.yang.factory.abstractmethodFactory;

public class Test {
    public static void main(String[] args) {
        IphoneAbstractFactory iphoneAbstractFactory = new IphoneAbstractFactoryImpl();
        iphoneAbstractFactory.newBattery().print();
        iphoneAbstractFactory.newScreen().print();
    }
}
