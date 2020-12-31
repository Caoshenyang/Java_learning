package com.yang.spring5.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 学生
 * @author: caoshenyang
 * @date: 2020.12.31
 */
public class Student {

    private String[] course;

    private List<String> lists;

    private Map<String, String> maps;

    private Set<String> sets;

    public void setCourse(String[] course) {
        this.course = course;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    @Override
    public String toString() {
        return "Student{" +
                "course=" + Arrays.toString(course) +
                ", lists=" + lists +
                ", maps=" + maps +
                ", sets=" + sets +
                '}';
    }
}
