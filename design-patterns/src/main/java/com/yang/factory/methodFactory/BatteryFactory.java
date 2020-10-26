package com.yang.factory.methodFactory;

public class BatteryFactory implements IphoneAbstractFactory {
    @Override
    public IphoneInterface newIphoneFitting() {
        System.out.println("电池配件工厂开工-->生产电池");
        return new Battery();
    }
}
