# 一、对象的创建与销毁

## 1.用静态工厂代替构造器

### 优点

1. **方法名称的多样性**

> 构造方法：与类同名的方法，用来创建对象。
>
> 所以利用构造方法来创建对象，方法的名称已经限定死了，只能与类同名，我们想要创建不同的对象，只能通过修改构造方法的参数列表（利用重载）来进行扩展。
>
> 当具有多个相同签名的构造方法时，在开发过程中，可能存在调用错误构造器的风险。
>
> 用静态工厂代替，可以通过不同的方法名称加以区分，代码可读性更好。

2. **不必每次创建新对象**

> 利用构造器创建对象，每次都要 new 一个新的对象，浪费资源
>
> 静态工厂可以预先创建好对象，或者将创建好的对象缓存起来，之后可以重复利用。

3. **返回原类型的任意子类对象**

> 我们在选择返回对象的类时有了更大的灵活性

4. **每次返回的对象的类可以不同**

> 我们可以通过参数控制返回不同的类的对象

5. **方法返回对象所属的类，在编写包含该静态工厂方法的类时可以不存在**

> 这种灵活的静态工厂方法构成了服务提供者框架（ Service Provider Framework ）的基础
>
> 服务提供者框架四个重要的组件
>
> - 服务接口：提供者实现的服务接口
> - 服务提供者接口（可选）：用来生成服务接口对象的工厂对象，如果没有，可以通过反射来生成服务对象。
> - 提供者注册API：这是用来给提供者注册服务的API
> - 服务访问API：客户端用来获取服务实例的。

定义往往是比较抽象的，结合代码来看一下，帮助我们理解。

```java
/**
 * @Description: 服务接口 提供登录服务
 * @author: caoshenyang
 * @date: 2020.12.21
 */
public interface LoginService {
    public void login();
}

/**
 * @Description: 服务提供者接口，用来获取服务对象
 * @author: caoshenyang
 * @date: 2020.12.21
 */
public interface Provider {
    public LoginService getLoginService();
}

/**
 * @Description: 客户端包含四大组成部分最后两种，提供者注册API和服务访问API
 * @author: caoshenyang
 * @date: 2020.12.21
 */
public class ServiceClient {
    /**
     * 用Map来保存注册的服务
     */
    private static final Map<String, Provider> providers = new HashMap<String, Provider>();

    /**
     * 提供者注册API,用于提供者注册服务
     */
    public static void registeredProvider(String name, Provider provider) {
        providers.put(name, provider);
    }

    /**
     * 服务访问API，用来获取服务对象 通过传入一个名字在注册服务Map里去捞想要的服务对象（静态工厂）
     */
    public static LoginService getService(String name) {
        Provider provider = providers.get(name);
        if (provider == null) {
            throw new IllegalArgumentException("No provider registered with name=" + name);
        }
        //通过服务提供者接口获取,如果没有，可通过反射获取。
        return provider.getLoginService();
    }

}
```

这样我们就实现了一个动态的工厂，当我们需要添加新的服务时，我们只需要实现**服务提供者接口**和**服务接口**即可，然后通过服务注册时的名称，去工厂里捞对应的服务对象。



### 缺点

1. 类如果不含公有的或者受保护的构造器，就不能被子类化

> 子类化：可以最大程度的使用现用类的方法。可以理解为继承
>
> 私有化构造器将导致类无法被继承，这个时候建议使用复合。

2. 由于没有统一的命名规范，程序员很难发现它们

> 见到一下方法要留意是否为静态工厂方法
>
> **from、of、valueOf、instance、getInstance、create、newInstance、getType、newType、type**