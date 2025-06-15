package Util.factory;

import MyObject.User;

public class UserCreationFactory {

    // 创建新用户（注册使用）
    public static User createNewUser(String username, String password, String phoneNumber) {
        return new User(username, password, phoneNumber);
    }

    // 创建带有ID的用户（从数据库加载）
    public static User createUserWithId(int userId, String username, String password, String phoneNumber) {
        return new User(userId, username, password, phoneNumber);
    }

    // 创建完整信息的用户
    public static User createFullUser(int userId, String username, String sex, String password,
                                      String phoneNumber, double account) {
        return new User(userId, username, sex, password, phoneNumber, account);
    }

    // 创建登录用户对象（仅包含用户名和密码）
    public static User createLoginUser(String username, String password) {
        return new User(username, password);
    }

    // 创建空用户对象
    public static User createEmptyUser() {
        return new User();
    }
}
