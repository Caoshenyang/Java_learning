# final关键字

被 **final** 修饰时，通常指被修饰部分不能被改变。



修饰 **数据**、**方法**、**类**的作用：

**数据**

对于基本类型，**final** 使数值恒定不变，而对于对象引用，**final** 使引用恒定不变。

**方法**

- 方法：给方法上锁，防止子类通过覆写改变方法的行为
- 参数：在参数列表中，将参数声明为 **final** 意味着在方法中不能改变参数指向的对象或基本变量

**类**

当说一个类是 **final** （**final** 关键字在类定义之前），就意味着它不能被继承。