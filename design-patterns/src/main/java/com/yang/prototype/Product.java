package com.yang.prototype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product 产品类
 * 属性 productName 产品名称
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Cloneable {
    String productName = "iphone11";

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
