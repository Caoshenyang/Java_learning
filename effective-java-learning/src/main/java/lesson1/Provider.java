package lesson1;

/**
 * @Description: 服务提供者接口，用来获取服务对象
 * @author: caoshenyang
 * @date: 2020.12.21
 */
public interface Provider {
    public LoginService getLoginService();
}
