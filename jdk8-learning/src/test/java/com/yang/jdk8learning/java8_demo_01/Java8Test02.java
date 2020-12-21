package com.yang.jdk8learning.java8_demo_01;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;

class Java8Test02 {

    @Test
    public static void main(String[] args) {

        String afssd = myShow02(
                a -> a.toUpperCase(), "afssd");
        System.out.println(afssd);

    }

    public static String myShow02(MyShow02<String> myShow02, String s) {
        return myShow02.getShow(s);
    }

}