package com.yang.spring5;

/**
 * @Author: csy
 * @Date: 2020/12/17 20:38
 * @Description: 使用set方法进行属性注入
 */
public class Book {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("name：" + name);
    }

}
