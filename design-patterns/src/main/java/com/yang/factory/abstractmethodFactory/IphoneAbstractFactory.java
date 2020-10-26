package com.yang.factory.abstractmethodFactory;

//抽象工厂：提供了手机各个配件生产方法
public interface IphoneAbstractFactory {

    public IphoneInterface newBattery();

    public IphoneInterface newScreen();

}
