package com.yang.spring5.bean;

/**
 * @Description: 课程
 * @author: caoshenyang
 * @date: 2020.12.31
 */
public class Course {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
