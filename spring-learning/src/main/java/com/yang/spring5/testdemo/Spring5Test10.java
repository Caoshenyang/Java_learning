package com.yang.spring5.testdemo;

import com.yang.spring5.bean.Order;
import com.yang.spring5.service.StudentService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: csy
 * @Date: 2020/12/28 22:16
 * @Description:
 */
public class Spring5Test10 {

    @Test
    public void testAdd() {
        //1 加载spring配置文件
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean12.xml");
        //2 获取配置创建的对象
        StudentService studentService = context.getBean("studentService", StudentService.class);
        studentService.show();
    }

}
