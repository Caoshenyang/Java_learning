package com.yang.factory.methodFactory;

public class Test {
    public static void main(String[] args) {
        ScreenFactory screenFactory = new ScreenFactory();
        IphoneInterface iphoneInterface = screenFactory.newIphoneFitting();
        iphoneInterface.print();
    }
}
