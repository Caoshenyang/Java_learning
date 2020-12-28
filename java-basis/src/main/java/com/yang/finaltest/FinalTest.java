package com.yang.finaltest;

public class FinalTest {
    final int a;

    public FinalTest(int a) {
        this.a = a;
    }
}

class Test {
    public static void main(String[] args) {
        FinalTest finalTest = new FinalTest(1);
    }
}