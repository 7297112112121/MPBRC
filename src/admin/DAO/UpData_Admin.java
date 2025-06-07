package admin.DAO;

import admin.Admin;
import data.AdminLogin;
import util.db.DBUpData;
import java.util.HashMap;
/**
 * 数据更新业务——管理员
 * */
public class UpData_Admin implements AdminSQL {
        private static final HashMap<Integer, String> updata = new HashMap<>();

        /**
         * 方案一：一次性全部更新方法
         * 1、获得需要修改用户对象类型
         * 2、根据用户id查找该用户，覆盖原本数据库信息
         * 3、用户对象调用set方法更新信息
         * 3.1、更新用户登陆状态表与用户管理表
         * 4、返回Boolean值，若true则修改成功，否则false失败
         *
         * 传入参数：完整用户对象
         * */
        public static boolean upData(Admin frontAdmin) {//参数一：原登录管理员对象,参数二：现需要修改的管理员对象
            boolean fals = false;
            Admin loginadmin = AdminLogin.getAdmin(); //获得登录管理员对象
            //修改位置
            updata.put(1, frontAdmin.getName());
            updata.put(2, frontAdmin.getPassword());
            updata.put(3, frontAdmin.getPhone());
            updata.put(4, String.valueOf(frontAdmin.getWorkID()));
            //查找范围
            updata.put(5, String.valueOf(loginadmin.getID()));
            fals = DBUpData.update(SQL_UPDATE_Admin,updata) > 0;//更新数据库用户信息
            if (fals) {                                     //若更新成功
                AdminLogin.setAdmin(frontAdmin);                 //更新用户登陆状态表
            }
            updata.clear();           //清空HashMap，防止sql语句冲突
            return fals;
        }

        /**
         * 方案二：局部信息更新方法
         * */
    public static boolean upDataName(Object obj) {
        boolean fals = false; //默认错误
        return fals;
    }
}

