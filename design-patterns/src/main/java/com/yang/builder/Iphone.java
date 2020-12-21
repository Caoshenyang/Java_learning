package com.yang.builder;

import lombok.Data;

/**
 * Iphone 包含多个组成部件的复杂对象
 */
@Data
public class Iphone {
    //电池
    private Battery battery;
    //显示屏
    private Screen screen;

    //显示产品特性
    public void show() {
        battery.print();
        screen.print();
        System.out.println("iphone组装完成");
    }
}
