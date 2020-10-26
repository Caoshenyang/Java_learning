# **JAVA** 8 新特性
**Java 8 应该是目前项目中使用最多的版本，之前有使用过它的一些新特性，了解一些基本的用法，但是对于一些理论性的概念不是很清楚，最近看了一些教程和博客，收获很大，在这里记录一下。**

## 介绍
Java 8 新增了非常多的新特性，包括一些数据结构的优化，JVM的优化，这里只记录一些日常中用到的新特性：

1. Lambda表达式
2. 方法引用
3. 函数式接口
4. 默认方法
5. Stream
6. Optional 
7. 新的日期API 



### 1. Lambda

**Lambda 允许把函数作为一个方法的参数（函数作为参数传递进方法中）。**
**使用 Lambda 表达式可以使代码变的更加简洁紧凑。**

   - 先来感受一下从 匿名内部类 到 Lambda 的转换

        - 案例一

          ```java
          //匿名内部类
          Runnable r = new Runnable() {
              @Override
              public void run() {
                  System.out.println("Hello World!");
              }
          };
          
          //Lambda 表达式
          Runnable r2 = () -> System.out.println("Hello World!");
          
          r.run();
          r2.run();
          ```

     - 案例二

       ```java
       //使用匿名内部类作为参数传递
       TreeSet<String> t = new TreeSet<>(new Comparator<String>() {
           @Override
           public int compare(String o1, String o2) {
               return Integer.compare(o1.length(),o2.length());
           }
       });
       
       //Lambda 表达式作为参数传递
       TreeSet<String> t2 = new TreeSet<>((o1, o2) -> Integer.compare(o1.length(), o2.length()));
       ```

       通过上面两个案例可以看出，Lambda 是一个**匿名函数**，我们可以把 Lambda 表达式理解为是**一段可以传递的代码**（将代码像数据一样进行传递）。Lambda 明显的减少了代码量。

- Lambda表达式语法

  - 语法格式

  ```java
  //引入了新的语法元素和操作符，"->" 箭头操作符将表达式分成了两部分
  //左边 表达式所需要的参数 右边 表达式将要执行的功能
  (parameters) -> expression
  (parameters) -> { statements;}
  
  ```

  - 语法特性

    - 可选类型声明：不需要声明参数类型，编译器可以统一识别参数值。
    - 可选的参数圆括号：一个参数无需定义圆括号，但多个参数需要定义圆括号。
    - 可选的大括号：如果主体包含了一个语句，就不需要使用大括号。
    - 可选的返回关键字：如果主体只有一个表达式返回值则编译器会自动返回值，使用大括号时需要指明表达式return的值。




### 2. 函数式接口

**就是一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口。**

- 自定义函数式接口

```java
@FunctionalInterface
public interface MyShow {
    public String getShow();
}

//函数式接口中使用泛型
@FunctionalInterface
public interface MyShow02<T> {
    public T getShow(T t);
}
```

- Lambda 作为参数传递时，接收的参数类型必须是与 Lambda 兼容的函数式接口

```java
public static String myShow02(MyShow02<String> myShow02,String s){
        return myShow02.getShow(s);
}

//Lambda作为参数传递
String afssd = myShow02(a -> a.toUpperCase(), "afssd");
System.out.println(afssd);
```

- Java 内置的**四大核心**函数式接口

  |          函数式接口           | 参数类型 | 返回类型 |                             用途                             |
  | :---------------------------: | :------: | :------: | :----------------------------------------------------------: |
  |  Consumer<T> <br/>消费型接口  |    T     |   void   |  对类型为T的对象应用操作。<br/>包含方法： void accept(T t);  |
  |  Supplier<T><br/>供给型接口   |    无    |    T     |          返回类型为T的对象。<br/>包含方法：T get();          |
  | Function<T，R><br/>函数型接口 |    T     |    R     | 对类型为T的对象应用操作，并返回结果。结果是R类型的对象。<br/> 包含方法：R apply(T t); |
  |  Predicate<T><br/>断定型接口  |    T     | boolean  | 确定类型为T的对象是否满足某约束，并返回boolean 值。<br/>包含方法boolean test(T t); |

- 其它接口

  | 函数式接口                                               |        参数类型         |        返回类型         |                             用途                             |
  | :------------------------------------------------------- | :---------------------: | :---------------------: | :----------------------------------------------------------: |
  | BiFunction<T, U, R>                                      |          T, U           |            R            | 对类型为 T, U 参数应用操作，返回 R 类型的结果。<br />包含方法为：R apply(T t, U u); |
  | UnaryOperator<T><br />(Function子接口)                   |            T            |            T            | 对类型为T的对象进行一元运算，并返回T类型的结果。<br/>包含方法为T apply(T t); |
  | BinaryOperator<T><br/>(BiFunction 子接口)                |            T            |            T            | 对类型为T的对象进行二元运算，并返回T类型的结果。<br />包含方法为T apply(T t1, T t2); |
  | BiConsumer<T, U>                                         |          T, U           |          void           | 对类型为T, U 参数应用操作。包含方法为void accept(T t, U u)； |
  | IntFunction<R><br/>LongFunction<R><br/>DoubleFunction<R> |            T            | int<br/>long<br/>double |           分 别 计 算 int 、 long 、double值的函数           |
  | IntFunction<R><br/>LongFunction<R><br/>DoubleFunction<R> | int<br/>long<br/>double |            R            |           R 参数分别为int、long、double 类型的函数           |



### 3. 方法引用

**当要传递给Lambda体的操作，已经有实现的方法了，可以使用方法引用！**

**方法引用与Lambda结合使用可以使代码更加简洁，紧凑。**

- 语法

  - 使用操作符`" :: "`，将方法名和对象或类的名字分隔开来。

- 使用场景

  - **Lambda表达式的主体仅包含一个表达式，且该表达式仅调用一个已经存在的方法**
  - **方法引用所使用的方法的入参和返回值与Lambda表达式实现的函数式接口的入参和返回值一致**

- 方法引用

  - 方法引用的四种形式

    ```java
    1. 静态方法引用　　　　　　　：　 　ClassName :: staticMethodName
    2. 构造器引用　　　　　　　　：　 　ClassName :: new
    3. 特定类的任意对象的实例方法引用：　 　ClassName :: instanceMethodName
    4. 特定对象的实例方法引用　　：　 　object :: instanceMethodName
    ```

  - 案例

    - 静态方法引用

      **Lambda表达式主体仅调用某个类的静态方法时使用。**

      ```java
      class Java8Test03 {
          public static void main(String args[]) {
              //使用Lambda表达式
              Arrays.asList("张三1", "李四1", "王二1").stream().forEach((x) -> Java8Test03.show(x));
              //使用静态方法引用
              Arrays.asList("张三2", "李四2", "王二2").stream().forEach(Java8Test03::show);
          }
      	//静态方法
          public static void show(String name) {
              System.out.println(name + "show!");
          }
      }
      ```

      ```java
      张三1show!
      李四1show!
      王二1show!
      张三2show!
      李四2show!
      王二2show!
      ```

    - 构造器引用

      **Lambda表达式主体仅调用某个类构造方法返回实例时使用。**

      ```java
      class Java8Test04 {
          public static void main(String args[]) {
              //使用Lambda表达式
              Function<Integer, Integer[]> fun = (n) -> new Integer[n];
              //使用构造器引用
              Function<Integer, Integer[]> fun2 = Integer[]::new;
          }
      }
      ```

    - 特定类的任意对象的实例方法引用

      1. **方法引用所使用方法的入参和返回值要与Lambda表达式实现的函数式接口的入参和返回值一致。**
      2. **Lambda表达式的第一个入参为实例方法的调用者，后面的入参与实例方法一致。**

      ```java
      class MyUser {
      
          private String name;
          private Integer age;
      
          public void setNameAndAge(String name, Integer age) {
              this.name = name;
              this.age = age;
              System.out.println("name: " + name + "age: " + age);
          }
      
          public static void main(String args[]) {
              //lambda表达式的用法：
              TestInterface testInterface1 = (myUser, name, age) -> myUser.setNameAndAge(name, age);
              testInterface1.set(new MyUser(), "张三", 18);
              //类的任意对象的实例方法引用的用法:
              TestInterface testInterface2 = MyUser::setNameAndAge;
              testInterface2.set(new MyUser(), "李四", 25);
          }
      
          @FunctionalInterface
          interface TestInterface {
              // 注意：入参比User类的setNameAndAge方法多1个MyUser对象，除第一个外其它入参类型一致
              public void set(MyUser myUser, String name, Integer age);
          }
      }
      ```

      ```java
      name: 张三age: 18
      name: 李四age: 25
      ```

    - 特定对象的实例方法引用

      **Lambda表达式主体中仅调用了某个对象的某个实例方法时使用。**

      ```java
      class Java8Test05 {
          public static void main(String args[]) {
              //使用Lambda表达式
              Arrays.asList("张三1", "李四1", "王二1").stream().forEach((x) -> Java8Test03.show(x));
              //使用静态方法引用
              Arrays.asList("张三2", "李四2", "王二2").stream().forEach(Java8Test03::show);
          }
      	//实例方法
          public void show(String name) {
              System.out.println(name + "show!");
          }
      }
      ```

      ```java
      张三1show!
      李四1show!
      王二1show!
      张三2show!
      李四2show!
      王二2show!
      ```

      

### 4. 默认方法

**默认方法就是接口可以有实现方法，而且不需要实现类去实现这个方法。**

**我们只需要在方法名前加`default`关键字即可标记该方法为默认方法。**

- 为什么要有这个特性？

```java
首先，之前的接口是个双刃剑，好处是面向抽象而不是面向具体编程，缺陷是，当需要修改接口时候，需要修改全部实现该接口的类，目前的 java 8 之前的集合框架没有 foreach 方法，通常能想到的解决办法是在JDK里给相关的接口添加新的方法及实现。然而，对于已经发布的版本，是没法在给接口添加新方法的同时不影响已有的实现。所以引进的默认方法。他们的目的是为了解决接口的修改与现有的实现不兼容的问题。
```

- 语法

  ```java
  public interface TestInterface {
      default void print() {
          System.out.println("我是默认方法。");
      }
  }
  ```

- 多个默认方法

  我们知道一个类可以实现多个接口，那么如果恰巧这些接口拥有相同的默认方法，实现类在调用默认方法时，会选择使用哪个接口的默认方法呢？

  为了解决这种情况的冲突，Java 8 提供了以下三条原则：

  1. 类中的方法优先级最高，类或父类中申明的方法优先级高于任何声明为默认方法的优先级。
  2. 如果第一条无法判断，那么子接口的优先级更高：方法签名相同时，优先选择拥有最具实现的默认方法的接口，即B继承了A，那么B比A更具体。
  3. 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法， 显式地选择使用哪一个默认方法的实现。

### 5. Stream

**是数据渠道，用于操作数据源（集合、数组等）所生成的元素序列。**

**集合注重数据存储，流注重计算！**

- 注意

  1. Stream 自己不会存储元素。
  2. Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
  3. Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

- Stream操作的三个步骤

  - 创建 Stream

    一个数据源（如：集合、数组），获取一个流。以下是获取流的三种方式：

    - Java 8中的Collection接口被扩展，提供了两个获取流的方法

      ```java
      //返回一个顺序流
      default Stream<E> stream() {
          return StreamSupport.stream(spliterator(), false);
      }
      //返回一个并行流 （并行流就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。）
      default Stream<E> parallelStream() {
          return StreamSupport.stream(spliterator(), true);
      }
      ```

    - Arrays 的静态方法 stream() 可以获取数组流

      ```java
      //返回一个流
      public static <T> Stream<T> stream(T[] array) {
          return stream(array, 0, array.length);
      }
      
      ```

      ```java
      //重载形式，能够处理对应基本类型的数组：
      public static IntStream stream(int[] array) {
          return stream(array, 0, array.length);
      }
      public static IntStream stream(int[] array, int startInclusive, int endExclusive) {
          return StreamSupport.intStream(spliterator(array, startInclusive, endExclusive), false);
      }
      public static LongStream stream(long[] array) {
          return stream(array, 0, array.length);
      }
      public static LongStream stream(long[] array, int startInclusive, int endExclusive) {
          return StreamSupport.longStream(spliterator(array, startInclusive, endExclusive), false);
      }
      public static DoubleStream stream(double[] array) {
          return stream(array, 0, array.length);
      }
      public static DoubleStream stream(double[] array, int startInclusive, int endExclusive) {
          return StreamSupport.doubleStream(spliterator(array, startInclusive, endExclusive), false);
      }
      
      ```

    - 由值创建流

      ```java
      //可以使用静态方法 Stream.of(), 通过显示值创建一个流。它可以接收任意数量的参数。
      public static<T> Stream<T> of(T t) {
          return StreamSupport.stream(new Streams.StreamBuilderImpl<>(t), false);
      }
      ```

    - 由函数创建流：创建无限流

      ```java
      //迭代
      public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f) {...}
      //生成
      public static<T> Stream<T> generate(Supplier<T> s) {...}
      
      ```

  - 中间操作

    一个中间操作链，对数据源的数据进行处理

    多个**中间操作**可以连接起来形成一个**流水线**，除非流水线上触发终止操作，否则**中间操作不会执行任何的处理**！而在**终止操作时一次性全部处理，称为“惰性求值”**

    - 筛选与切片

      |        方法         |                             描述                             |
      | :-----------------: | :----------------------------------------------------------: |
      | filter(Predicate p) |             接收 Lambda ， 从流中排除某些元素。              |
      |     distinct()      | 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素 |
      | limit(long maxSize) |                截断流，使其元素不超过给定数量                |
      |    skip(long n)     | 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补 |

    - 映射

      |              方法               |                             描述                             |
      | :-----------------------------: | :----------------------------------------------------------: |
      |         map(Function f)         | 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。 |
      | mapToDouble(ToDoubleFunction f) | 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream。 |
      |    mapToInt(ToIntFunction f)    | 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream。 |
      |   mapToLong(ToLongFunction f)   |         接收一个函数作为参数，该函数会被应用到每个元         |
      |       flatMap(Function f)       | 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流 |

    - 排序

      |          方法           |                描述                |
      | :---------------------: | :--------------------------------: |
      |        sorted()         |  产生一个新流，其中按自然顺序排序  |
      | sorted(Comparator comp) | 产生一个新流，其中按比较器顺序排序 |

      

  - 中止操作（终端操作）

    一个终止操作，执行中间操作链，并产生结果

    终端操作会从流的流水线生成结果。其结果可以是任何不是流的值，例如：List、Integer，甚至是 void 。

    - 查找与匹配

      |          方法          |                             描述                             |
      | :--------------------: | :----------------------------------------------------------: |
      | allMatch(Predicate p)  |                     检查是否匹配所有元素                     |
      | anyMatch(Predicate p)  |                   检查是否至少匹配一个元素                   |
      | noneMatch(Predicate p) |                   检查是否没有匹配所有元素                   |
      |      findFirst()       |                        返回第一个元素                        |
      |       findAny()        |                    返回当前流中的任意元素                    |
      |        count()         |                       返回流中元素总数                       |
      |   max(Comparator c)    |                        返回流中最大值                        |
      |   min(Comparator c)    |                        返回流中最小值                        |
      |  forEach(Consumer c)   | **内部迭代**<br />(使用 Collection 接口需要用户去做迭代，称为**外部迭代** ) |

    - 归约

      |               方法               |                           描述                            |
      | :------------------------------: | :-------------------------------------------------------: |
      | reduce(T iden, BinaryOperator b) |      可以将流中元素反复结合起来，得到一个值。返回 T       |
      |     reduce(BinaryOperator b)     | 可以将流中元素反复结合起来，得到一个值。返回 Optional\<T> |

    - 收集

      |         方法         |                             描述                             |
      | :------------------: | :----------------------------------------------------------: |
      | collect(Collector c) | 将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法 |

    

    Collector 接口中方法的实现决定了如何对流执行收集操作(如收集到 List、Set、Map)。但是 Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例，具体方法与实例如下表：

    ​	

    |       方法        |        返回类型        |                             作用                             |                             示例                             |
    | :---------------: | :--------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
    |      toList       |        List\<T>        |                     把流中元素收集到List                     | List<Employee> emps= list.stream()<br />.collect(Collectors.toList()); |
    |       toSet       |        Set\<T>         |                     把流中元素收集到Set                      | Set<Employee> emps= list.stream()<br />.collect(Collectors.toSet()); |
    |   toCollection    |     Collection\<T>     |                  把流中元素收集到创建的集合                  | Collection<Employee>emps=list.stream()<br />.collect(Collectors.toCollection(ArrayList::new)); |
    |     counting      |          Long          |                      计算流中元素的个数                      | long count = list.stream()<br />.collect(Collectors.counting()); |
    |    summingInt     |        Integer         |                   对流中元素的整数属性求和                   | inttotal=list.stream()<br />.collect(Collectors.summingInt(Employee::getSalary)); |
    |   averagingInt    |         Double         |               计算流中元素Integer属性的平均值                | doubleavg= list.stream()<br />.collect(Collectors.averagingInt(Employee::getSalary)); |
    |  summarizingInt   |  IntSummaryStatistics  |           收集流中Integer属性的统计值。如：平均值            | IntSummaryStatisticsiss= list.stream()<br />.collect(Collectors.summarizingInt(Employee::getSalary)); |
    |      joining      |         String         |                      连接流中每个字符串                      | String str= list.stream()<br />.map(Employee::getName).collect(Collectors.joining()); |
    |       maxBy       |      Optional\<T>      |                     根据比较器选择最大值                     | Optional<Emp>max= list.stream()<br />.collect(Collectors.maxBy(comparingInt(Employee::getSalary))); |
    |       minBy       |      Optional\<T>      |                     根据比较器选择最小值                     | Optional<Emp> min = list.stream()<br />.collect(Collectors.minBy(comparingInt(Employee::getSalary))); |
    |     reducing      |     归约产生的类型     | 从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值 | inttotal=list.stream()<br />.collect(Collectors.reducing(0, Employee::getSalar, Integer::sum)); |
    | collectingAndThen |   转换函数返回的类型   |              包裹另一个收集器，对其结果转换函数              | inthow= list.stream()<br />.collect(Collectors.collectingAndThen(Collectors.toList(), List::size)); |
    |    groupingBy     |    Map<K, List\<T>>    |            根据某属性值对流分组，属性为K，结果为V            | Map<Emp.Status, List<Emp>> map= list.stream()<br />.collect(Collectors.groupingBy(Employee::getStatus)); |
    |  partitioningBy   | Map<Boolean, List\<T>> |                   根据true或false进行分区                    | Map<Boolean,List\<Emp>>vd= list.stream()<br />.collect(Collectors.partitioningBy(Employee::getManage)); |

    

### 6. Optional

**`Optional<T> 类(java.util.Optional) 是一个容器类，代表一个值存在或不存在，原来用 null 表示一个值不存在，现在 Optional 可以更好的表达这个概念。并且可以避免空指针异常。**

- 常用方法

  ```java
  Optional.of(T t) : 创建一个 Optional 实例
  Optional.empty() : 创建一个空的 Optional 实例
  Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
  isPresent() : 判断是否包含值
  orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
  orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
  map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
  flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
  ```



### 7. Date Time API

**Date Time API (JSR 310)进一步加强对日期与时间的处理**

- 为什么要发布新的 **Date Time API** ?

  旧版Java中，日期时间API存在诸多问题，其中有：

  - **非线程安全**  -  `java.util.Date` 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
  - **设计很差** − Java的日期/时间类的定义并不一致，在`java.util`和`java.sql`的包中都有日期类，此外用于格式化和解析的类在`java.text`包中定义。`java.util.Date`同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入`java.sql`包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
  - **时区处理麻烦** − 日期类并不提供国际化，没有时区支持，因此`Java`引入了`java.util.Calendar`和`java.util.TimeZone`类，但他们同样存在上述所有的问题。

  新的`java.time`包涵盖了所有处理日期，时间，日期/时间，时区，时刻（`instants`），过程（`during`）与时钟（`clock`）的操作。以下为两个比较重要的 API：

  - **Local(本地)** − 简化了日期时间的处理，没有时区的问题。
  - **Zoned(时区)** − 通过制定的时区处理日期时间。



- **LocalDate、LocalTime、LocalDateTime**

  `LocalDate`、`LocalTime`、`LocalDateTime `类的实例是不可变的对象，分别表示使用 ISO-8601日历系统的日期、时间、日期和时间。它们提供了简单的日期或时间，并不包含当前的时间信息。也不包含与时区相关的信息。

  ```java
  public static void main(String[] args) {
          /**
           * LocalDate
           *
           * getYear()        当前日期年份信息
           * getMonth()       当前日期月份信息
           * getDayOfMonth()  当前日期是一个月中的第几天
           * getDayOfWeek()   当前日期是周几
           * lengthOfMonth()  当前月有多少天
           * isLeapYear       是否是闰年
           */
          System.out.println("*******************LocalDate*******************");
          LocalDate localDate = LocalDate.of(2020, 9, 1);
          System.out.println(localDate.getYear() + "\t" + 
                             localDate.getMonth() + "\t" + 
                             localDate.getDayOfMonth() + "\t" + 
                             localDate.getDayOfWeek() + "\t" + 
                             localDate.lengthOfMonth() + "\t" + 
                             localDate.isLeapYear());
          LocalDate now = LocalDate.now();
          System.out.println(now.get(ChronoField.YEAR) + "\t" + 
                             now.get(ChronoField.MONTH_OF_YEAR) + "\t" +
                             now.get(ChronoField.DAY_OF_MONTH));
  
          /**
           * LocalTime
           *
           * getHour      时
           * getMinute    分
           * getSecond    秒
           */
          System.out.println("*******************LocalTime*******************");
          LocalTime localTime = LocalTime.of(20, 44, 12);
          System.out.println(localTime.getHour() + "\t" + 
                             localTime.getMinute() + "\t" + 
                             localTime.getSecond());
  
          /**
           * 解析字符串
           * 默认格式: yyyy-MM-dd
           */
          System.out.println("*******************解析字符串*******************");
          LocalDate localDate2 = LocalDate.parse("2020-09-01");
          System.out.println(localDate2.toString());
  
          /**
           * 解析字符串
           * 默认格式: HH:mm:ss.SSS
           */
          LocalTime localTime2 = LocalTime.parse("20:42:12.828");
          System.out.println(localTime2.toString());
  
          /**
           * 互相进行类型转换
           */
          System.out.println("*******************互相进行类型转换*******************");
          
          LocalDateTime localDateTime1 = LocalDateTime
              							.of(2020, 9, 1, 16, 12, 10, 888)
              							.atZone(ZoneId.of("Asia/Shanghai"))
              							.toLocalDateTime();
          System.out.println(localDateTime1);
          
          //LocalDate + LocalTime -> LocalDateTime
          LocalDateTime localDateTime2 = LocalDateTime.of(localDate2, localTime2);
          System.out.println(localDateTime2);
          
          //组合拼接
          LocalDateTime localDateTime3 = localDate2.atTime(10, 10, 10);
          System.out.println(localDateTime3);
          
          LocalDateTime localDateTime4 = localDate2.atTime(localTime2);
          System.out.println(localDateTime4);
          
          LocalDateTime localDateTime5 = localTime2.atDate(localDate2);
          System.out.println(localDateTime5);
          
          LocalDateTime localDateTime6 = 
              LocalDateTime.parse("2020/09/01 16:19:20.888", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"));
          System.out.println(localDateTime6);
          LocalDate localDate3 = localDateTime6.toLocalDate();
          System.out.println(localDate3);
          LocalTime toLocalTime3 = localDateTime6.toLocalTime();
          System.out.println(toLocalTime3);
      }
  ```

  ```java
  ******************* LocalDate *******************
  2020	SEPTEMBER	1	TUESDAY	30	true
  2020	9	2
  ******************* LocalTime *******************
  20	44	12
  ******************* 解析字符串 *******************
  2020-09-01
  20:42:12.828
  ******************* 互相进行类型转换 *******************
  2020-09-01T16:12:10.000000888
  2020-09-01T20:42:12.828
  2020-09-01T10:10:10
  2020-09-01T20:42:12.828
  2020-09-01T20:42:12.828
  2020-09-01T16:19:20.888
  2020-09-01
  16:19:20.888
  
  ```

- **Instatnt**

  Instant对时间的建模方式是以`UTC`时区的1970年1月1日午夜时分开始所经历的秒数进行计算，它不包含时区信息。

  ```java
   public static void main(String[] args) {
          //获取当前时间戳
          long milli = Instant.now().toEpochMilli();
          System.out.println("milli：" + milli);
          //根据某个时间戳获取Instant实例
          Instant instant = Instant.ofEpochMilli(milli);
          System.out.println("instant：" + instant);
  
          //minusSeconds() 减一秒 
          Instant instant2 = instant.minusSeconds(1L);
          System.out.println("instant2：" + instant2);
  
          //isBefore()和isAfter()比较大小
          System.out.println(instant.isAfter(instant2));
      }
  ```

- **Duration和Period**

  `Duration` 用于计算两个“时间”间隔， `Period `用于计算两个“日期”间隔

  ```java
   public static void main(String[] args) {
          Duration d1 = Duration.between(LocalDateTime.of(2020, 9, 1, 15, 55, 55, 888), LocalDateTime.now());
          Duration d2 = Duration.between(LocalTime.of(17, 55, 10), LocalTime.now());
          Duration d3 = Duration.between(Instant.ofEpochMilli(1599037854143L), Instant.now());
          System.out.println(d3.toMinutes());
  
          //Duration对象用秒和纳秒来衡量时间的长短，所以入参不能使用LocalDate类型, 否则抛UnsupportedTemporalTypeException: Unsupported unit: Seconds
          //Duration.between(LocalDate.of(2019, 10, 7), LocalDate.now());
  
          //如果想要对多个时间对象进行日期运算，可以用Period
          Period p1 = Period.between(LocalDate.of(2019, 1, 1), LocalDate.now());
          System.out.println(p1.getYears() + "\t" + p1.getMonths() + "\t" + p1.getDays());
  
      }
  ```

- **TemporalAdjuster** 

  `TemporalAdjuster` 时间校正器。有时我们可能需要获取例如：将日期调整到“下个周日”等操作。

  `TemporalAdjusters` : 该类通过静态方法提供了大量的常用 TemporalAdjuster 的实现。

  ```java	
  public static void main(String[] args) {
          LocalDateTime localDateTime = LocalDateTime.now();
          // 本年本月最后一天
          System.out.println(localDateTime.with(TemporalAdjusters.lastDayOfMonth()));
          // 本年本月第一天
          System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfMonth()));
          // 本年下一月第一天
          System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfNextMonth()));
          // 下一年第一天
          System.out.println(localDateTime.with(TemporalAdjusters.firstDayOfNextYear()));
          // 本年最后一天
          System.out.println(localDateTime.with(TemporalAdjusters.lastDayOfYear()));
          // 下一个周五
          System.out.println(localDateTime.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
          // 本月第一个周五
          System.out.println(localDateTime.with(TemporalAdjusters.firstInMonth(DayOfWeek.FRIDAY)));
          // 本月最后一个周五
          System.out.println(localDateTime.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));
          // 下一个周五，如果当前是周五则返回当前时间
          System.out.println(localDateTime.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)));
          // 前一个周五
          System.out.println(localDateTime.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY)));
          // 前一个周五，如果当前是周五则返回当前时间
          System.out.println(localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.FRIDAY)));
      }
  ```

  ```java
  2020-09-30T17:45:14.274
  2020-09-01T17:45:14.274
  2020-10-01T17:45:14.274
  2021-01-01T17:45:14.274
  2020-12-31T17:45:14.274
  2020-09-04T17:45:14.274
  2020-09-04T17:45:14.274
  2020-09-25T17:45:14.274
  2020-09-04T17:45:14.274
  2020-08-28T17:45:14.274
  2020-08-28T17:45:14.274
  ```

  

- **DateTimeFormatter**

  `java.time.format.DateTimeFormatter` 类,该类提供了三种格式化方法：

  - 预定义的标准格式

  - 语言环境相关的格式

  - 自定义的格式

  ```java
  public static void main(String[] args) {
          //日期转字符串
          LocalDate ld = LocalDate.of(2020, 9, 1);
          String s1 = ld.format(DateTimeFormatter.BASIC_ISO_DATE);
          System.out.println(s1);
          String s2 = ld.format(DateTimeFormatter.ISO_LOCAL_DATE);
          System.out.println(s2);
          //字符串转日期
          LocalDateTime ld1 = LocalDateTime.parse("2020-09-01 18:00:00.888", 								  					DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
          System.out.println(ld1);
      }
  ```

  ```java
  20200901
  2020-09-01
  2020-09-01T18:00:00.888
  ```

  

- **时区的处理**

  `Java8` 中加入了对时区的支持，带时区的时间为分别为：`ZonedDate`、`ZonedTime`、`ZonedDateTime`

  其中每个时区都对应着 ID，地区ID都为 “{区域}/{城市}”的格式

  ```java
  public static void main(String[] args) {
          //获取所有合法的“区域/城市”字符串
          Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
          //availableZoneIds.forEach(System.out::println);
          
      	//获取系统默认时区
          ZoneId systemZoneId = ZoneId.systemDefault();
          System.out.println("当期时区: " + systemZoneId);
  
          // 获取当前时间日期
          ZonedDateTime date1 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");
          System.out.println("date1: " + date1);
  
          //创建时区
          ZoneId id = ZoneId.of("Europe/Paris");
          System.out.println("ZoneId: " + id);
  
          //LocalDate、LocalDateTime、Instant 转 ZonedDateTime
          ZonedDateTime zdt1 = LocalDate.of(2020, 9, 3).atStartOfDay(ZoneId.systemDefault());
          ZonedDateTime zdt2 = LocalDateTime.of(2020, 9, 3, 14, 10, 55, 888)
              							.atZone(ZoneId.of("Asia/Shanghai"));
          ZonedDateTime zdt3 = Instant.now().atZone(ZoneId.of("Asia/Yerevan"));
  
          //Instant转LocalDateTime
          LocalDateTime ldt1 = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
      }
  ```

  



































