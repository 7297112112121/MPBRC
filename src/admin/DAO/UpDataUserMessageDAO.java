package admin.DAO;

import data.UserForm;
import user.User;

import java.util.Collection;

public class UpDataUserMessageDAO {
    /**
     * 加载所有用户数据
     * */
    public static void loadUserALL() {
        //重新从数据库加载所有的信息
        UserForm.loadUsers();
    }
}
