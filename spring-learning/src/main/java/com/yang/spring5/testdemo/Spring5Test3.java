package com.yang.spring5.testdemo;

import com.yang.spring5.bean.Emp;
import com.yang.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: csy
 * @Date: 2020/12/28 22:16
 * @Description:
 */
public class Spring5Test3 {

    @Test
    public void testAdd() {
        //1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        //2 获取配置创建的对象
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }

}
