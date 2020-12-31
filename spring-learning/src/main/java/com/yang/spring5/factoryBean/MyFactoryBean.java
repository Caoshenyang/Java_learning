package com.yang.spring5.factoryBean;


import com.yang.spring5.bean.Course;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Description: 工厂bean
 * @author: caoshenyang
 * @date: 2020.12.31
 */
public class MyFactoryBean implements FactoryBean<Course> {
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setName("JAVA");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
