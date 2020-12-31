package com.yang.spring5.bean;

/**
 * @Description: 员工
 * @author: caoshenyang
 * @date: 2020.12.31
 */
public class Emp {

    private String name;
    private String gender;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", dept=" + dept +
                '}';
    }
}
