package com.yang.factory.methodFactory;

//具体工厂类 生产显示屏
public class ScreenFactory implements IphoneAbstractFactory {
    @Override
    public IphoneInterface newIphoneFitting() {
        System.out.println("显示屏工厂开工-->生产显示屏");
        return new Screen();
    }
}
