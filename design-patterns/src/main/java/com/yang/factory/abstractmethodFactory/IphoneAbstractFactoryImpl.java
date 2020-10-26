package com.yang.factory.abstractmethodFactory;

public class IphoneAbstractFactoryImpl implements IphoneAbstractFactory {
    @Override
    public IphoneInterface newBattery() {
        System.out.println("电池配件工厂开工-->生产电池");
        return new Battery();
    }

    @Override
    public IphoneInterface newScreen() {
        System.out.println("显示屏工厂开工-->生产显示屏");
        return new Screen();
    }
}
