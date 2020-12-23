package lesson2;

/**
 * @Description: 客户端
 * @author: caoshenyang
 * @date: 2020.12.23
 */
public class Client {

    public static void main(String[] args) {
        User build = new User.Builder("张三", "12").gender("男").hobby("打游戏").build();
        System.out.println(build);
    }

}
