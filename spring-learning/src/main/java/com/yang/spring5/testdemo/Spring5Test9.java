package com.yang.spring5.testdemo;

import com.yang.spring5.bean.Course;
import com.yang.spring5.bean.Order;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: csy
 * @Date: 2020/12/28 22:16
 * @Description:
 */
public class Spring5Test9 {

    @Test
    public void testAdd() {
        //1 加载spring配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean9.xml");
        //2 获取配置创建的对象
        Order order = context.getBean("order", Order.class);
        System.out.println("第四步 获取创建bean实例对象");
        System.out.println(order);

        context.close();
    }

}
