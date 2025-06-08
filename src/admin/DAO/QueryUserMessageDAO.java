package admin.DAO;

import data.UserForm;
import user.User;

import java.util.Collection;
import java.util.HashMap;

public class QueryUserMessageDAO {
    /**
     * 查询所有最新用户数据
     * */
    public static Collection<User> queryAll() {
        return UserForm.getUserList();
    }


}
