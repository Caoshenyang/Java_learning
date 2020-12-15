# Spring学习

## 一、Spring框架概述

1. Spring 是轻量级的开源JavaEE 框架
2. Spring 可以解决企业应用开发的复杂性
3. Spring 的两个核心部分：IOC 和 AOP
   - IOC: 控制反转，把创建对象的过程交给Spring 进行管理
   - AOP: 面向切面，不修改源代码进行功能增强
4. Spring 特点
   - 方便解耦，简化开发
   - AOP编程支持
   - 方便程序测试
   - 方便和其他框架进行整和
   - 方便进行事务操作
   - 降低API开发难度

## 二、Sping 初体验

1. 创建一个maven项目

2. 引入spring5.x版本基础jar包

   core、context、aop、expression (commons-logging 非Spring框架内)

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-core</artifactId>
       <version>5.2.9.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.2.9.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-aop</artifactId>
       <version>5.2.9.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-expression</artifactId>
       <version>5.2.9.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>commons-logging</groupId>
       <artifactId>commons-logging</artifactId>
       <version>1.2</version>
   </dependency>
   ```


3. 创建一个普通类，在这个类创建普通方法

   ```java
   public class User {
       public void add() {
           System.out.println("add...");
       }
   }
   ```

4. 创建Spring配置文件，在配置文件配置创建的对象

   - Spring配置文件使用xml
   - 创建bean1.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   
       <!--配置User对象创建-->
       <bean id="user" class="com.yang.spring5.User"></bean>
   </beans>
   ```

5. 进行测试编码

   ```java
   public class Spring5Test {
   
       @Test
       public void testAdd() {
           //1 加载spring配置文件
           ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
   
           //2 获取配置创建的对象
           User user = context.getBean("user", User.class);
   
           System.out.println(user);
           user.add();
       }
   }
   ```

## 三、IOC容器

1. IOC概率和原理

   - 什么是IOC?

   > 控制反转，把对象创建和对象之间的调用过程，交给spring管理
   >
   > 使用IOC的目的：为了降低耦合度

   - IOC底层原理

   > 主要使用了xml解析、工厂模式、反射

   - 

2. IOC接口（BeanFactory）

3. IOC操作Bean管理（基于xml）

4. IOC操作Bean管理（基于注解）