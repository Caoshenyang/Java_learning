package com.yang.jdk8learning.java8_demo_01;

class MyUser {

    private String name;
    private Integer age;

    public void setNameAndAge(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("name: " + name + "age: " + age);
    }

    public static void main(String args[]) {
        //lambda表达式的用法：
        TestInterface testInterface1 = (myUser, name, age) -> myUser.setNameAndAge(name, age);
        testInterface1.set(new MyUser(), "张三", 18);
        //类的任意对象的实例方法引用的用法:
        TestInterface testInterface2 = MyUser::setNameAndAge;
        testInterface2.set(new MyUser(), "李四", 25);
    }

    @FunctionalInterface
    interface TestInterface {
        // 注意：入参比User类的setNameAndAge方法多1个MyUser对象，除第一个外其它入参类型一致
        public void set(MyUser myUser, String name, Integer age);
    }
}