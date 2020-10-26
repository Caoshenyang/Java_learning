package com.yang.prototype;

import java.util.ArrayList;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("iphone11"));
        productList.add(new Product("iphone11 Max"));
        productList.add(new Product("iphone11 Pro"));
        productList.add(new Product("iphone11 Pro Max"));
        Object clone = ((ArrayList<Product>) productList).clone();
        System.out.println(clone);


    }
}
