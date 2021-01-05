package com.yang.spring5.service;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: caoshenyang
 * @date: 2021.01.05
 */
//在注解里面 value 属性值可以省略不写，
//默认值是类名称，首字母小写
//StudentService -- studentService
@Service(value = "studentService") //注解等同于XML配置文件：<bean id="studentService" class=".."/>
public class StudentService {
    public void show() {
        System.out.println("service show ......");
    }
}
