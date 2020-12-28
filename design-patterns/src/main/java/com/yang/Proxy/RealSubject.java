package com.yang.Proxy;

/**
 * @Description: 真实目标对象
 * @author: caoshenyang
 * @date: 2020.12.03
 */
public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("访问真实主题方法...");
    }
}
