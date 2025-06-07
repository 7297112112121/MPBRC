package user.Service;

import java.util.ArrayList;
import java.util.List;

public class UserAction {
    public static UserAction message = new UserAction();
    private List<String> users = new ArrayList<>();
    public UserAction() {};

    public void add(String action) {
        users.add(action);
    }

    /**
     * 返回List集合
     */
    public List<String> getAll() {
        return users;
    }

    ///返回指定元素
    public String get(int index) {
        return users.get(index);
    }

    /// 获得单例
    public static UserAction getMessage() {
        return message;
    }
}
