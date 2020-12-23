package lesson2;


/**
 * @Description: user
 * @author: caoshenyang
 * @date: 2020.12.22
 */
public class User {

    private String username;
    private String age;
    private String gender;
    private String hobby;

    /**
     * 构造器
     */
    public static class Builder {

        // 必须的参数
        private String username;
        private String age;
        //可选的参数 可以在这里设置默认值
        private String gender = "保密";
        private String hobby;

        public Builder(String username, String age) {
            this.username = username;
            this.age = age;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder hobby(String hobby) {
            this.hobby = hobby;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    private User(Builder builder) {
        username = builder.username;
        age = builder.age;
        gender = builder.gender;
        hobby = builder.hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
