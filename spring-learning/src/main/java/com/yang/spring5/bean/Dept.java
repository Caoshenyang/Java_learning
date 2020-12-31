package com.yang.spring5.bean;

/**
 * @Description: 部门
 * @author: caoshenyang
 * @date: 2020.12.31
 */
public class Dept {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
