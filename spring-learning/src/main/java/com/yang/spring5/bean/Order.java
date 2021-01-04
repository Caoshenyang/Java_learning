package com.yang.spring5.bean;

/**
 * @Description: 订单
 * @author: caoshenyang
 * @date: 2021.01.04
 */
public class Order {
    private String name;

    public Order() {
        System.out.println("第一步 执行无参构造方法创建 bean 实例");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("第二步 调用setter方法设置属性值");
    }

    public void initMethod(){
        System.out.println("第三步 执行初始化方法");
    }

    public void destroyMethod(){
        System.out.println("第五步 执行销毁方法");
    }
}
