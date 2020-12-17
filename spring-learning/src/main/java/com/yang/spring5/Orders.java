package com.yang.spring5;

/**
 * @Author: csy
 * @Date: 2020/12/17 21:15
 * @Description:
 */
public class Orders {

    private String name;

    public Orders(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name："+ name);
    }
}
