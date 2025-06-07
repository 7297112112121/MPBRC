package admin.Serve;

import admin.Admin;
import admin.DAO.AdminSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.db.DBQuary;
import util.db.DBUpData;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * 注册业务
 * */
public class Register implements  AdminSQL {
    private static final Logger logger = LogManager.getLogger(Register.class);
    private static final HashMap<Integer, String> regi = new HashMap<>();
    /**
     * 执行用户注册，主要处理注册异常
     * 默认注册失败，返回false
     * */
    public static boolean register(Object obj) {
        boolean fals = false;
        try {
            //按类型注册
            fals = Register.checkIdentity(obj);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            regi.clear();           //清空HashMap，防止sql语句冲突
        }
        return fals;
    }

    /**
     * 注册核心，不同对象注册
     * 若要添加或删除注册表请在这里操作
     * */
    private static boolean checkIdentity(Object identity) throws SQLException {
        boolean fals = false;                                       //默认无需注册
            if (identity instanceof Admin) {
            Admin admin = (Admin) identity;
            regi.put(1, admin.getName());
            regi.put(2, admin.getPassword());
            if (!DBQuary.query(SQL_FIND_ADMIN, regi).next()) {      //需要注册
                regi.put(3, admin.getPhone());
                regi.put(4, admin.getWorkID());
                fals = DBUpData.update(SQL_INSER_ALL_ADMIN, regi) > 0;//注册成功返回true，否则返回false
            }
        }
        return fals;                                                //单一出口
    }
}
