package com.yang.spring5.bean;

import java.util.List;

/**
 * @Description: 教师
 * @author: caoshenyang
 * @date: 2020.12.31
 */
public class Teacher {

   private List<Course> courses;

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "courses=" + courses +
                '}';
    }
}
