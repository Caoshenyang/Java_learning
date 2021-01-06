# Spring学习

[TOC]

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

### 1. IOC概率和原理

- 什么是IOC ?

> 控制反转，把对象创建和对象之间的调用过程，交给spring管理
>
> 使用IOC的目的：为了降低耦合度

- IOC底层原理

> 主要使用了xml解析、工厂模式、反射

- IOC过程

  - 第一步 xml配置文件，配置要创建的对象

  ```xml
  <!--配置User对象创建-->
  <bean id="user" class="com.yang.spring5.User"></bean>
  ```

  - 第二步 创建工厂类，通过xml解析，获取class属性值，再通过反射创建对象

  

### 2. IOC接口（BeanFactory）

- IOC思想是基于IOC容器完成的，IOC容器的底层就是对象工厂

- Spring提供IOC容器实现的两种方式

  - BeanFactory：IOC容器基本实现，Spring内部使用接口，不提供开发人员使用

  > 加载配置文件的时候不会创建对象，获取对象的时候才会去创建对象

  - ApplicationCpntext：BeanFactory的子接口，功能更加强大，一般由开发人员使用

  > 加载配置文件的时候就会把配置文件中的对象进行创建
  >
  > ```java
  > 加载配置文件的两个实现类
  > - ClassPathXmlApplicationContext
  > - FileSystemXmlApplicationContext
  > 
  > 这两个实现类的区别
  > ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");//类路径
  > ApplicationContext context = new FileSystemXmlApplicationContext("D:\\Java_learning\\spring-learning\\src\\main\\resources\\bean1.xml");//绝对路径
  > ```

### 3. Bean管理

#### 什么是Bean管理？

> Bean管理实际上是指两个操作
>
> 1. Spring创建对象
> 2. Spring注入属性

#### IOC操作Bean管理（基于xml）

- ##### 在Spring配置文件中，使用bean标签，标签里添加对应属性，就可以实现对象创建。

```xml
<!--配置User对象创建-->
<bean id="user" class="com.yang.spring5.User"></bean>
```

- ##### bean标签中常用属性

  - id：唯一标识
  - class：类全路径
  - name：类似与id属性，区别是name可以加特殊标识，如 “/” 等。

- ##### 创建对象的时候，默认执行无参构造方法，完成对象创建。

- ##### 属性注入（DI）

  > **set注入、构造器注入、P名称空间注入**

  - **第一种：使用set方法注入。**

  > 1. 创建类，定义属性和它的set方法
  >
  > ```java
  > /**
  >  * @Author: csy
  >  * @Date: 2020/12/17 20:38
  >  * @Description: 使用set方法进行属性注入
  >  */
  > public class Book {
  > 
  >     private String name;
  > 
  >     public void setName(String name) {
  >         this.name = name;
  >     }
  >     
  >     public void show() {
  >         System.out.println("name：" + name);
  >     }
  > }
  > ```
  >
  > 2. 在spring配置文件中，配置对象创建和属性注入
  >
  > ```xml
  > <!--配置Book对象创建-->
  > <bean id="book" class="com.yang.spring5.Book">
  >     <!--使用property完成属性注入-->
  >     <property name="name" value="语文"></property>
  > </bean>
  > ```
  >
  > 3. 测试
  >
  > ```java
  > public class Spring5Test {
  > 
  >     @Test
  >     public void testAdd() {
  >         //1 加载spring配置文件
  >         ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
  >         //2 获取配置创建的对象
  >         Book book = context.getBean("book", Book.class);
  >         book.show();
  >     }
  > }
  > ```
  >
  > 4. 输出
  >
  > ```java
  > name：语文
  > ```
  >

  - **第二种：使用有参构造方法注入。**

  > 1. 创建类，定义属性和有参构造方法。
  >
  > ```java
  > /**
  >  * @Author: csy
  >  * @Date: 2020/12/17 21:15
  >  * @Description: 使用有参构造方法注入
  >  */
  > public class Orders {
  > 
  >     private String name;
  > 
  >     public Orders(String name) {
  >         this.name = name;
  >     }
  > 
  >     public void show(){
  >         System.out.println("name："+ name);
  >     }
  > }
  > ```
  >
  > 2. 在Spring配置文件中，配置对象创建和属性注入
  >
  > ```xml
  > <!--配置Orders对象创建-->
  > <bean id="orders" class="com.yang.spring5.Orders">
  >     <!--使用 constructor-arg 完成属性注入-->
  >     <constructor-arg name="name" value="销售订单"/>
  > </bean>
  > ```
  >
  > 3. 测试
  >
  > ```java
  > public class Spring5Test {
  > 
  >     @Test
  >     public void testAdd() {
  >         //1 加载spring配置文件
  >         ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
  >         //2 获取配置创建的对象
  >         Orders orders = context.getBean("orders", Orders.class);
  >         orders.show();
  >     }
  > }
  > ```
  >
  > 4. 结果
  >
  > ```java
  > name：销售订单
  > ```

  - **第三种：P名称空间注入**

    **使用P名称空间注入，可以简化基于XML配置方式**

  >1. 在配置文件中添加P名称空间
  >
  >```xml
  > xmlns:p="http://www.springframework.org/schema/p"
  >```
  >
  >2. 配置对象创建和属性注入
  >
  >```xml
  ><?xml version="1.0" encoding="UTF-8"?>
  ><beans xmlns="http://www.springframework.org/schema/beans"
  >       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >       xmlns:p="http://www.springframework.org/schema/p"
  >       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  >
  >    <!--使用P标签注入-->
  >    <bean id="book" class="com.yang.spring5.Book" p:name="数学">
  >    </bean>
  >
  ></beans>
  >```

- ##### 特殊类型值注入

  - null 

  > ```xml
  > <!--通过<null></null>标签注入-->
  > <!--配置Book对象创建-->
  > <bean id="book" class="com.yang.spring5.Book">
  >     <!--使用property完成属性注入-->
  >     <property name="name">
  >         <null></null>
  >     </property>
  > </bean>
  > ```

  - 特殊符号

  ```xml
  <!--1.把特殊符号转义-->
  <!--2.把带特殊符号的内容写到CDATA中-->
  <!--配置Book对象创建-->
  <bean id="book" class="com.yang.spring5.Book">
      <!--使用property完成属性注入-->
      <property name="name">
          <value><![CDATA[<<语文>>]]></value>
      </property>
  </bean>
  ```

- ##### 注入属性-外部bean

  > 场景：service类中引用dao类
  >
  > 代码示例：
  >
  > 1. 创建两个类，service类和dao类
  > 2. 在service类中调用dao里的方法
  > 3. 在spring配置文件中进行配置
  >
  > ```java
  > public class UserService {
  > 
  >     /**
  >      * 创建UserDao类型属性，生成set方法
  >      */
  >     private UserDao userDao;
  > 
  >     public void setUserDao(UserDao userDao) {
  >         this.userDao = userDao;
  >     }
  > 
  >     public void add() {
  >         System.out.println("service add....");
  >         userDao.update();
  >     }
  > }
  > ```
  >
  > ```java
  > public interface UserDao {
  >     public void update();
  > }
  > ```
  >
  > ```java
  > public class UserDaoImpl implements UserDao {
  > 
  >     @Override
  >     public void update() {
  >         System.out.println("dao update....");
  >     }
  > }
  > ```
  >
  > ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
  > <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  > 
  >     <bean id="userService" class="com.yang.spring5.service.UserService">
  >         <!--  注入userDao对象    -->
  >         <!--  name属性：类里的属性名称   -->
  >         <!--  ref属性：创建userDao对象bean标签id值 -->
  >         <property name="userDao" ref="userDao"></property>
  >     </bean>
  >     <bean id="userDao" class="com.yang.spring5.dao.UserDaoImpl"></bean>
  > </beans>
  > ```
  >
  > ```java
  > public class Spring5Test2 {
  > 
  >     @Test
  >     public void testAdd() {
  >         //1 加载spring配置文件
  >         ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
  >         //2 获取配置创建的对象
  >         UserService userService = context.getBean("userService", UserService.class);
  >         userService.add();
  >     }
  > 
  > }
  > ```

- ##### 注入属性-内部bean和级联赋值

  > **内部bean**
  >
  > 场景：员工和部门（一对多关系）
  >
  > 一个部门有多个员工，一个员工属于一个部门
  >
  > 部门是一，员工是多
  >
  > 代码实例：
  >
  > 1. 创建员工类与部门类（员工类中有部门属性）
  > 2. xml配置文件中进行内部bean配置
  >
  > ```java
  > package com.yang.spring5.bean;
  > 
  > /**
  >  * @Description: 部门
  >  * @author: caoshenyang
  >  * @date: 2020.12.31
  >  */
  > public class Dept {
  > 
  >     private String name;
  > 
  >     public void setName(String name) {
  >         this.name = name;
  >     }
  > 
  >     @Override
  >     public String toString() {
  >         return "Dept{" +
  >                 "name='" + name + '\'' +
  >                 '}';
  >     }
  > }
  > ```
  >
  > ```java
  > package com.yang.spring5.bean;
  > 
  > /**
  >  * @Description: 员工
  >  * @author: caoshenyang
  >  * @date: 2020.12.31
  >  */
  > public class Emp {
  > 
  >     private String name;
  >     private String gender;
  >     private Dept dept;
  > 
  >     public void setName(String name) {
  >         this.name = name;
  >     }
  > 
  >     public void setGender(String gender) {
  >         this.gender = gender;
  >     }
  > 
  >     public void setDept(Dept dept) {
  >         this.dept = dept;
  >     }
  > 
  >     @Override
  >     public String toString() {
  >         return "Emp{" +
  >                 "name='" + name + '\'' +
  >                 ", gender='" + gender + '\'' +
  >                 ", dept=" + dept +
  >                 '}';
  >     }
  > }
  > ```
  >
  > ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
  > <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  > 
  >     <bean name="emp" class="com.yang.spring5.bean.Emp">
  >         <property name="name" value="张三"></property>
  >         <property name="gender" value="男"></property>
  >         <!--内部bean注入-->
  >         <!--当然也可以采用外部bean的方式来实现 1.外部创建dept的bean 2.内部通过ref标签引用-->
  >         <property name="dept" >
  >             <bean id="dept" class="com.yang.spring5.bean.Dept">
  >                 <property name="name" value="IT部门"></property>
  >             </bean>
  >         </property>
  >     </bean>
  > </beans>
  > ```
  >
  > ```java
  > package com.yang.spring5.testdemo;
  > 
  > import com.yang.spring5.bean.Emp;
  > import com.yang.spring5.service.UserService;
  > import org.junit.Test;
  > import org.springframework.context.ApplicationContext;
  > import org.springframework.context.support.ClassPathXmlApplicationContext;
  > 
  > /**
  >  * @Author: csy
  >  * @Date: 2020/12/28 22:16
  >  * @Description:
  >  */
  > public class Spring5Test3 {
  > 
  >     @Test
  >     public void testAdd() {
  >         //1 加载spring配置文件
  >         ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
  >         //2 获取配置创建的对象
  >         Emp emp = context.getBean("emp", Emp.class);
  >         System.out.println(emp);
  >     }
  > }
  > ```
  >
  > 输出：
  >
  > ```java
  > Emp{name='张三', gender='男', dept=Dept{name='IT部门'}}
  > ```
  >
  > **级联赋值**
  >
  > ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
  > <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  > 
  >     <bean name="emp" class="com.yang.spring5.bean.Emp">
  >         <property name="name" value="张三"></property>
  >         <property name="gender" value="男"></property>
  >         <!--内部bean注入-->
  >         <!--当然也可以采用外部bean的方式来实现 1.外部创建dept的bean 2.内部通过ref标签引用-->
  >         <property name="dept" ref="dept" > </property>
  >         <!--依赖于get方法获取dept对象，然后给dept对象属性赋值，所以在Emp对象中需要实现对应的get方法-->
  > 		<property name="dept.name" value="财务部门"></property>
  >     </bean>
  >     <bean id="dept" class="com.yang.spring5.bean.Dept"></bean>
  > </beans>
  > ```
  >
  > ```java
  > package com.yang.spring5.testdemo;
  > 
  > import com.yang.spring5.bean.Emp;
  > import org.junit.Test;
  > import org.springframework.context.ApplicationContext;
  > import org.springframework.context.support.ClassPathXmlApplicationContext;
  > 
  > /**
  >  * @Author: csy
  >  * @Date: 2020/12/28 22:16
  >  * @Description:
  >  */
  > public class Spring5Test4 {
  > 
  >     @Test
  >     public void testAdd() {
  >         //1 加载spring配置文件
  >         ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml");
  >         //2 获取配置创建的对象
  >         Emp emp = context.getBean("emp", Emp.class);
  >         System.out.println(emp);
  >     }
  > }
  > ```
  >
  > 输出：
  >
  > ```java
  > Emp{name='张三', gender='男', dept=Dept{name='财务部门'}
  > ```
  
- ##### 注入集合属性

  - 注入**数组**类型属性
  - 注入**List**集合属性
  - 注入**Map**集合属性
  - 注入**Set**集合属性

  > 场景：当我们需要注入集合类型属性值的时候
  >
  > 代码示例：
  >
  > 1. 创建Stu类
  > 2. xml配置
  >
  > ```java
  > package com.yang.spring5.bean;
  > 
  > import java.util.Arrays;
  > import java.util.List;
  > import java.util.Map;
  > import java.util.Set;
  > 
  > /**
  >  * @Description: 学生
  >  * @author: caoshenyang
  >  * @date: 2020.12.31
  >  */
  > public class Student {
  > 
  >     private String[] course;
  > 
  >     private List<String> lists;
  > 
  >     private Map<String, String> maps;
  > 
  >     private Set<String> sets;
  > 
  >     public void setCourse(String[] course) {
  >         this.course = course;
  >     }
  > 
  >     public void setLists(List<String> lists) {
  >         this.lists = lists;
  >     }
  > 
  >     public void setMaps(Map<String, String> maps) {
  >         this.maps = maps;
  >     }
  > 
  >     public void setSets(Set<String> sets) {
  >         this.sets = sets;
  >     }
  > 
  >     @Override
  >     public String toString() {
  >         return "Student{" +
  >                 "course=" + Arrays.toString(course) +
  >                 ", lists=" + lists +
  >                 ", maps=" + maps +
  >                 ", sets=" + sets +
  >                 '}';
  >     }
  > }
  > ```
  >
  > ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
  > <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  > 
  >     <bean name="student" class="com.yang.spring5.bean.Student">
  >         <property name="course">
  >             <array>
  >                 <value>语文</value>
  >                 <value>数学</value>
  >             </array>
  >         </property>
  >         <property name="lists">
  >             <list>
  >                 <value>JAVA</value>
  >                 <value>C语言</value>
  >             </list>
  >         </property>
  >         <property name="maps">
  >             <map>
  >                 <entry key="1" value="MySql"/>
  >                 <entry key="2" value="Redis"/>
  >             </map>
  >         </property>
  >         <property name="sets">
  >             <set>
  >                 <value>C++</value>
  >                 <value>C#</value>
  >             </set>
  >         </property>
  >     </bean>
  > </beans>
  > ```
  >
  > 输出：
  >
  > ```java
  > Student{course=[语文, 数学], lists=[JAVA, C语言], maps={1=MySql, 2=Redis}, sets=[C++, C#]}
  > ```
  >
  > 

- ##### 在集合里设置对象类型值

  > 场景：教师类的课程属性为课程类
  >
  > 1. 创建2个类Teacher类和Course类
  > 2. xml配置
  >
  > 代码示例：
  >
  > ```java
  > package com.yang.spring5.bean;
  > 
  > import java.util.List;
  > 
  > /**
  >  * @Description: 教师
  >  * @author: caoshenyang
  >  * @date: 2020.12.31
  >  */
  > public class Teacher {
  > 
  >    private List<Course> courses;
  > 
  >     public void setCourses(List<Course> courses) {
  >         this.courses = courses;
  >     }
  > 
  >     @Override
  >     public String toString() {
  >         return "Teacher{" +
  >                 "courses=" + courses +
  >                 '}';
  >     }
  > }
  > ```
  >
  > ```java
  > package com.yang.spring5.bean;
  > 
  > /**
  >  * @Description: 课程
  >  * @author: caoshenyang
  >  * @date: 2020.12.31
  >  */
  > public class Course {
  > 
  >     private String name;
  > 
  >     public void setName(String name) {
  >         this.name = name;
  >     }
  > 
  >     @Override
  >     public String toString() {
  >         return "Course{" +
  >                 "name='" + name + '\'' +
  >                 '}';
  >     }
  > }
  > ```
  >
  > ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
  > <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  > 
  >     <bean name="teacher" class="com.yang.spring5.bean.Teacher">
  >         <property name="courses">
  >             <list>
  >                 <ref bean="course1"/>
  >                 <ref bean="course2"/>
  >             </list>
  >         </property>
  > 
  >     </bean>
  > 
  >     <bean name="course1" class="com.yang.spring5.bean.Course">
  >         <property name="name" value="JAVA"/>
  >     </bean>
  >     <bean name="course2" class="com.yang.spring5.bean.Course">
  >         <property name="name" value="VUE"/>
  >     </bean>
  > </beans>
  > ```
  >
  > 输出：
  >
  > ```java
  > Teacher{courses=[Course{name='JAVA'}, Course{name='VUE'}]}
  > ```



- ##### 把集合注入部分提取出来

  > 1. spring配置文件中引入名称空间util
> 2. 使用util标签完成list集合注入提取
  >
> ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xmlns:util="http://www.springframework.org/schema/util"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
>        http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
  > 
  > 
  >     <util:list id="courses">
  >         <ref bean="course1"/>
  >         <ref bean="course2"/>
  >     </util:list>
  > 
  >     <bean name="teacher" class="com.yang.spring5.bean.Teacher">
  >         <property name="courses" ref="courses"/>
  >     </bean>
  > 
  >     <bean name="course1" class="com.yang.spring5.bean.Course">
  >         <property name="name" value="JAVA"/>
  >     </bean>
  >     <bean name="course2" class="com.yang.spring5.bean.Course">
  >         <property name="name" value="VUE"/>
  >     </bean>
  > </beans>
  > ```
  >
  > 输出：
  >
  > ```java
  > Teacher{courses=[Course{name='JAVA'}, Course{name='VUE'}]}
  > ```

- ##### FactoryBean

  - 普通bean：xml中定义什么类型的bean，即返回什么类型的bean

  > 前面介绍的都为普通bean

  - 工厂bean：xml中定义的类型可以和返回的类型不一样

  > 代码示例：
  >
  > 1. 创建工厂类，实现FactoryBean接口
  > 2. 实现接口中的方法，在方法中定义返回的类型
>
  > ```java
> package com.yang.spring5.factoryBean;
  > 
> import com.yang.spring5.bean.Course;
  > import org.springframework.beans.factory.FactoryBean;
  > 
  > /**
  >  * @Description: 工厂bean
  >  * @author: caoshenyang
  >  * @date: 2020.12.31
  >  */
  > public class MyFactoryBean implements FactoryBean<Course> {
  >     @Override
  >     public Course getObject() throws Exception {
  >         Course course = new Course();
  >         course.setName("JAVA");
  >         return course;
  >     }
  > 
  >     @Override
  >     public Class<?> getObjectType() {
  >         return null;
  >     }
  > 
  >     @Override
  >     public boolean isSingleton() {
  >         return false;
  >     }
  > }
  > ```
  >
  > ```xml
  > <?xml version="1.0" encoding="UTF-8"?>
  > <beans xmlns="http://www.springframework.org/schema/beans"
  >        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  >        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  > 
  >     <bean name="myFactoryBean" class="com.yang.spring5.factoryBean.MyFactoryBean"></bean>
  > </beans>
  > ```
  >
  > ```java
  > package com.yang.spring5.testdemo;
  > 
  > import com.yang.spring5.bean.Course;
  > import com.yang.spring5.bean.Teacher;
  > import org.junit.Test;
  > import org.springframework.context.ApplicationContext;
  > import org.springframework.context.support.ClassPathXmlApplicationContext;
  > 
  > /**
  >  * @Author: csy
  >  * @Date: 2020/12/28 22:16
  >  * @Description:
  >  */
  > public class Spring5Test8 {
  > 
  >     @Test
  >     public void testAdd() {
  >         //1 加载spring配置文件
  >         ApplicationContext context = new ClassPathXmlApplicationContext("bean8.xml");
  >         //2 获取配置创建的对象
  >         Course course = context.getBean("myFactoryBean", Course.class);
  >         System.out.println(course);
  >     }
  > 
  > }
  > ```
  >
  > 输出：
  >
  > ```java
  > Course{name='JAVA'}
  > ```

#### IOC操作Bean管理（基于注解）

> 1. 什么是注解
>
>     （1）注解是代码特殊标记，格式：@注解名称(属性名称=属性值, 属性名称=属性值…)
>
>     （2）使用注解，注解作用在类上面，方法上面，属性上面
>
>     （3）使用注解目的：简化 xml 配置
>
> 2. Spring针对Bean管理提供的注解（下面四个注解功能一样，都可以用来创建实例）
>
>     （1）@Component
>
>     （2）@Service
>
>     （3）@Controller
>
>     （4）@Repository
>
> 3. 基于注解方式实现对象创建
>
>    （1）引入AOP依赖
>
>    ```xml
>    <dependency>
>        <groupId>org.springframework</groupId>
>        <artifactId>spring-aop</artifactId>
>        <version>5.2.9.RELEASE</version>
>    </dependency>
>    ```
>
>    （2）开启注解扫描
>
>    ```xml
>    <?xml version="1.0" encoding="UTF-8"?>
>    <beans xmlns="http://www.springframework.org/schema/beans"
>           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>           xmlns:context="http://www.springframework.org/schema/context"
>           xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
>    http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
>    
>        <!--开启注解扫描
>            1.扫描多个包，多个包使用逗号隔开
>            2.扫描包的上层目录
>        -->
>        <context:component-scan base-package="com.yang.spring5"></context:component-scan>
>    </beans>
>    ```
>
>    （3）创建类，加上注解
>
>    ```java
>    //在注解里面 value 属性值可以省略不写，
>    //默认值是类名称，首字母小写
>    //StudentService -- studentService
>    @Service(value = "studentService") //注解等同于XML配置文件：<bean id="studentService" class=".."/>
>    public class StudentService {
>        public void show() {
>            System.out.println("service show ......");
>        }
>    }
>    ```
>
>    测试：
>
>    ```java
>    @Test
>    public void testAdd() {
>        //1 加载spring配置文件
>        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean12.xml");
>        //2 获取配置创建的对象
>        StudentService studentService = context.getBean("studentService", StudentService.class);
>        studentService.show();
>    }
>    ```
>
>    输出：
>
>    ```java
>    service show ......
>    ```
>
> 4. 开启组件扫描细节配置
>
> ```xml
> <!--示例 1
>      use-default-filters="false" 表示现在不使用默认 filter，自己配置 filter
>      context:include-filter ，设置扫描哪些内容
> -->
> <context:component-scan base-package="com.yang.spring5" use-default-filters="false">
>     <!--代表只扫描Controller注解的类-->
>     <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
> </context:component-scan>
> <!--示例 2
>  下面配置扫描包所有内容
>  context:exclude-filter： 设置哪些内容不进行扫描
> -->
> <context:component-scan base-package="com.yang.spring5">
>     <!--表示Controller注解的类之外一切都进行扫描-->
>     <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
> </context:component-scan>
> ```
>
> 5. 基于注解方式实现属性注入
>
>    （1）@AutoWired：根据属性类型进行自动装配
>
>    ​	第一步 把 service 和 dao 对象创建，在 service 和 dao 类添加创建对象注解
>
>    ​	第二步 在 service 注入 dao 对象，在 service 类添加 dao 类型属性，在属性上面使用注解
>
>    ```java
>    @Service
>    public class UserService {
>     //定义 dao 类型属性
>     //不需要添加 set 方法
>     //添加注入属性注解
>     @Autowired
>     private UserDao userDao;
>     public void add() {
>     System.out.println("service add.......");
>     userDao.add();
>     }
>    }
>    
>    //Dao实现类
>    @Repository
>    public class UserDaoImpl implements UserDao {
>        @Override
>        public void add() {
>            System.out.println("dao add.....");
>        }
>    }
>    ```
>
>    （2）@Qualifier：根据属性名称进行注入，这个@Qualifier 注解的使用，和上面@Autowired 一起使用
>
>    ```java
>    @Autowired
>    //根据名称进行注入（目的在于区别同一接口下有多个实现类，根据类型就无法选择，从而出错！）
>    @Qualifier(value = "userDaoImpl1") 
>    private UserDao userDao;
>    ```
>
>    （3）@Resource：可以根据类型注入，也可以根据名称注入
>
>    ```java
>    //@Resource //根据类型进行注入
>    @Resource(name = "userDaoImpl1") //根据名称进行注入
>    private UserDao userDao;
>    ```
>
>    （4）@Value：普通类型值注入
>
>    ```java
>    @Value(value = "abc")
>    private String name
>    ```
>
> 6. 完全注解开发
>
>    （1）创建配置类，替换xml配置文件
>
>    ```java
>    @Configuration //作为配置类，替代 xml 配置文件
>    @ComponentScan(basePackages = {"com.yang.spring5"})
>    public class SpringConfig {
>        
>    }
>    ```
>
>    （2）编写测试类
>
>    ```java
>    @Test
>    public void testService2() {
>     //加载配置类
>     ApplicationContext context
>     = new AnnotationConfigApplicationContext(SpringConfig.class);
>     UserService userService = context.getBean("userService",
>    UserService.class);
>     System.out.println(userService);
>     userService.add();
>    }
>    ```

### 4. Bean的作用域

bean作用域是用来确定从Spring容器中返回哪种类型的bean实例给调用者。

> 1. spring里配置的bean是单例还是多实例的？
>
>    默认为单例，可进行配置  使用 **scope** 标签， **prototype** 多实例 、 **singleton** 单例 (默认)
>
>    ```xml
>    <--多实例-->
>    <bean id="user" class="com.yang.spring5.User" scope="prototype"></bean>
>    <--单例-->
>    <bean id="user" class="com.yang.spring5.User" scope="singleton "></bean>
>    ```
>
> 2. **scope**设置**prototype** 和**singleton** 的区别？
>
>    1. **singleton** 单例  **prototype** 多实例
>    2. **singleton** bean在配置加载的时候进行创建，**prototype** bean在调用getBean方法的时候创建

### 5. Bean的生命周期

> 从对象创建到对象销毁的过程（5步）
>
> 1. 通过构造器创建 bean 实例（无参数构造）
>
> 2. 为 bean 的属性设置值和对其他 bean 引用（调用 set 方法）
>
> 3. 调用 bean 的初始化的方法（需要进行配置初始化的方法，调用init方法）
>
> 4. bean 可以使用了（对象获取到了，调用getBean方法）
>
> 5. 当容器关闭时候，调用 bean 的销毁的方法（需要进行配置销毁的方法，调用destroy方法）
>
> 代码示例
>
> ```java
> package com.yang.spring5.bean;
> 
> /**
>  * @Description: 订单
>  * @author: caoshenyang
>  * @date: 2021.01.04
>  */
> public class Order {
>     private String name;
> 
>     public Order() {
>         System.out.println("第一步 执行无参构造方法创建 bean 实例");
>     }
> 
>     public void setName(String name) {
>         this.name = name;
>         System.out.println("第二步 调用setter方法设置属性值");
>     }
> 
>     public void initMethod(){
>         System.out.println("第三步 执行初始化方法");
>     }
> 
>     public void destroyMethod(){
>         System.out.println("第五步 执行销毁方法");
>     }
> }
> ```
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
> 
>     <bean id="order" class="com.yang.spring5.bean.Order" init-method="initMethod" destroy-method="destroyMethod">
>         <property name="name" value="手机"/>
>     </bean>
> </beans>
> ```
>
> ```java
> @Test
> public void testAdd() {
>     //1 加载spring配置文件
>     ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean9.xml");
>     //2 获取配置创建的对象
>     Order order = context.getBean("order", Order.class);
>     System.out.println("第四步 获取创建bean实例对象");
>     System.out.println(order);
> 
>     context.close();
> }
> ```
>
> 输出：
>
> ```java
> 第一步 执行无参构造方法创建 bean 实例
> 第二步 调用setter方法设置属性值
> 第三步 执行初始化方法
> 第四步 获取创建bean实例对象
> com.yang.spring5.bean.Order@ed9d034
> 第五步 执行销毁方法
> ```
>
> 添加bean的后置处理器（bean的生命周期为7步）
>
> 1. 创建后置处理器类，需要实现BeanPostProcessor接口
> 2. 配置后置处理器
>
> ```java
> package com.yang.spring5.bean;
> 
> import org.springframework.beans.BeansException;
> import org.springframework.beans.factory.config.BeanPostProcessor;
> 
> /**
>  * @Description: 后置处理器
>  * @author: caoshenyang
>  * @date: 2021.01.04
>  */
> public class MyBeanPostProcessor implements BeanPostProcessor {
> 
>     @Override
>     public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
>         System.out.println("在初始化之前执行的方法");
>         return bean;
>     }
> 
>     @Override
>     public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
>         System.out.println("在初始化之后执行的方法");
>         return bean;
>     }
> }
> ```
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
> 
>     <bean id="order" class="com.yang.spring5.bean.Order" init-method="initMethod" destroy-method="destroyMethod">
>         <property name="name" value="手机"/>
>     </bean>
>     <!--配置后置处理器-->
>     <bean id="myBeanPostProcessor" class="com.yang.spring5.bean.MyBeanPostProcessor"></bean>
> </beans>
> ```
>
> ```java
> @Test
> public void testAdd() {
>     //1 加载spring配置文件
>     ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean9.xml");
>     //2 获取配置创建的对象
>     Order order = context.getBean("order", Order.class);
>     System.out.println("第四步 获取创建bean实例对象");
>     System.out.println(order);
> 
>     context.close();
> }
> ```
>
> 输出：
>
> ```java
> 第一步 执行无参构造方法创建 bean 实例
> 第二步 调用setter方法设置属性值
> 在初始化之前执行的方法
> 第三步 执行初始化方法
> 在初始化之后执行的方法
> 第四步 获取创建bean实例对象
> com.yang.spring5.bean.Order@4eb7f003
> 第五步 执行销毁方法
> ```

### 6. 引入外部属性文件

> 下面以配置数据库连接作为示例。

- 方式一：直接配置数据库信息

>1. 引入Druid连接池依赖
>2. 配置连接池
>
>```xml
><!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
><dependency>
>    <groupId>com.alibaba</groupId>
>    <artifactId>druid</artifactId>
>    <version>1.2.4</version>
></dependency>
>```
>
>```xml
><!--直接配置连接池-->
><bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
>    <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
>    <property name="url" value="jdbc:mysql://localhost:3306/userDb"></property>
>    <property name="username" value="root"></property>
>    <property name="password" value="root"></property>
></bean>
>```

- 方式二：引入外部属性文件配置数据库连接池

> 1. 创建外部属性文件，properties类型文件（jdbc.properties）
> 2. 写数据库信息
> 3. 引入到Spring配置文件中
>    1. 设置context名称空间
>    2. 引入数据
>
> ```properties
> prop.driverClass=com.mysql.jdbc.Driver
> prop.url=jdbc:mysql://localhost:3306/userDb
> prop.userName=root
> prop.password=root
> ```
>
> ```xml
> <?xml version="1.0" encoding="UTF-8"?>
> <beans xmlns="http://www.springframework.org/schema/beans"
>        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
>        xmlns:context="http://www.springframework.org/schema/context"
>        xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
> http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
> 
>     <!--引入外部属性文件-->
>     <context:property-placeholder location="classpath:jdbc.properties"/>
> 
>     <!--配置连接池-->
>     <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
>         <property name="driverClassName" value="${prop.driverClass}"></property>
>         <property name="url" value="${prop.url}"></property>
>         <property name="username" value="${prop.userName}"></property>
>         <property name="password" value="${prop.password}"></property>
>     </bean>
> </beans>
> ```

# AOP