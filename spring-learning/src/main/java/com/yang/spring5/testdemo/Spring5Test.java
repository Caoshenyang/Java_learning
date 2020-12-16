package com.yang.spring5.testdemo;

import com.yang.spring5.User;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @Author: csy
 * @Date: 2020/12/14 22:13
 * @Description:
 */
public class Spring5Test {

    @Test
    public void testAdd() {
        //1 加载spring配置文件
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        BeanFactory context = new FileSystemXmlApplicationContext("D:\\Java_learning\\spring-learning\\src\\main\\resources\\bean1.xml");
        //2 获取配置创建的对象
        User user = context.getBean("user", User.class);

        System.out.println(user);
        user.add();
    }
}
