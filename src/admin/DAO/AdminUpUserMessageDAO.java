package admin.DAO;

import data.UserForm;
import user.DAO.UpUserMessageDAO;
import user.User;

/**
 * 特意在创建一个用户信息更新，是为了防止用户更新DAO出现修改，需要修改管理员系统全部方法情况
 * */
public class AdminUpUserMessageDAO {
    /**
     * 加载所有用户数据
     * */
    public static void loadUserALL() {
        //重新从数据库加载所有的信息
        UserForm.loadUsers();
    }

    /**
     * 更新用户数据
     * */
    public static void upUserName(String name, int nameid) {
        UpUserMessageDAO.upDataName(name, nameid);
    }
    public static void upUserSex(String sex, int nameid) {
        UpUserMessageDAO.upDataSex(sex, nameid);
    }
    public static void upUserPhone(String phone, int nameid) {
        UpUserMessageDAO.upDataPhone(phone, nameid);
    }
    public static void upUserDeviceId(String deviceId, int nameid) {
        UpUserMessageDAO.upDataDeviceId(deviceId, nameid);
    }
    public static void upUserPassword(String password, int nameid) {
        UpUserMessageDAO.upDataPassword(password, nameid);
    }
    public static void upUserBalance(double balance, int nameid) {
        UpUserMessageDAO.upDataAccount(balance, nameid);
    }
    public static void upUserAdminID(int adminID, int nameid) {
        UpUserMessageDAO.upDataAdminId(adminID, nameid);
    }
}
