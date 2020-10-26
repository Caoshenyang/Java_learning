package com.yang;

interface IStudy {
    void study();
}

class Math implements IStudy {
    @Override
    public void study() {
        System.out.println("学习数学。");
    }
}

class Chinese implements IStudy {
    @Override
    public void study() {
        System.out.println("学习语文。");
    }
}

public class Student {
    public void study(IStudy iStudy) {
        iStudy.study();
    }
}

class Test {
    public static void main(String[] args) {
        Student student = new Student();
        student.study(new Math());
        student.study(new Chinese());
    }
}