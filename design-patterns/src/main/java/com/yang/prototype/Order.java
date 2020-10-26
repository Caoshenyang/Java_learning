package com.yang.prototype;

import lombok.Data;

/**
 * Order 订单类
 * 属性 serialNumber 基础类型 编码
 * 属性 Product     引用类型  商品
 */
@Data
public class Order implements Cloneable {
    int serialNumber = 1;
    Product product = new Product();

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Order order = (Order) super.clone();
        order.setProduct((Product) order.getProduct().clone());
        return order;
    }
}
