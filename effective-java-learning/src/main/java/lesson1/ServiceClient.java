package lesson1;


import java.util.HashMap;
import java.util.Map;

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
     * 服务访问API，用来获取服务对象 （静态工厂）
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
