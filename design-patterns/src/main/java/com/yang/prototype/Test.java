package com.yang.prototype;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {

        Order order = new Order();
        System.out.println(order);

        Order orderClone = (Order) order.clone();
        orderClone.setSerialNumber(2);
        orderClone.getProduct().setProductName("iphone12");

        System.out.println(order);
        System.out.println(orderClone);

    }
}
