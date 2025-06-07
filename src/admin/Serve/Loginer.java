package admin.Serve;

import admin.Admin;
import admin.DAO.AdminSQL;
import admin.DAO.RegularAdmin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import data.AdminLogin;
import util.db.DBQuary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 * 登陆业务
 * */
public class Loginer implements  AdminSQL {
    private static final Logger logger = LogManager.getLogger(Loginer.class);
    private static final Marker admin = MarkerManager.getMarker("ADMIN");
    private static final HashMap<Integer, String> logi = new HashMap<>();

    /**
     * 执行登录
     * 0、对象分类
     * 1、检查数据库是否有该条用户信息
     * 2、获取数据库该用户的所有信息
     * 3、生成新用户对象
     * 4、销毁形参对象
     * 默认用户不存在，返回false
     */

    //该方法专门捕获类型错误
    public static boolean login(Object obj) {
        boolean fals = false;
        try {
            //按类型注册
            fals = checkIdentity(obj);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            logi.clear();           //清空HashMap，防止sql语句冲突
        }
        return fals;
    }

    //该方法具体实现数据库操作
    private static boolean checkIdentity(Object identity) throws SQLException {
        boolean fals = false;                                   //默认登陆失败
        if (identity instanceof Admin) {
            Admin admin = (Admin) identity;
            logi.put(1, admin.getName());
            logi.put(2, admin.getPassword());
            ResultSet re = DBQuary.query(SQL_FIND_ADMIN, logi);
            fals = re.next();   //有结果返回true，无结果返回false
            if (fals) {
                //获得结果集的结果
                Admin ad = new RegularAdmin();
                int id = re.getInt("id");
                ad.setID(id);
                ad.setName(re.getString("name"));
                ad.setPassword(re.getString("password"));
                ad.setPhone(re.getString("phone"));
                ad.setWorkID(re.getString("workid"));

                AdminLogin.setAdmin(ad);                    //刷新管理员登陆状态表
            }
        }
        return fals;
    }
}
