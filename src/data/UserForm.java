package data;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import user.DAO.PaymentTransaction;
import user.User;
import global.db.DBQuary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * user表
 * */
public class UserForm {
    private static HashMap<Integer, User> userMap = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(PaymentTransaction.class);

    /**
     * 从数据库加载所有用户
     */
    public static void loadUsers() {
        try {
            String sql = "SELECT * FROM user";
            ResultSet res = DBQuary.query(sql);

            // 清空现有映射，避免重复
            userMap.clear();

            // 循环处理所有结果集记录
            while (res.next()) {
                User user = createUserFromResultSet(res);
                if (user != null) {
                    userMap.put(user.getNameID(), user);
                }
            }
        } catch (SQLException e) {
            logger.error("加载user表数据失败", e);
        }
    }

    /**
     * 加载数据库最新用户数据
     * */
    public static void loadNewUsers() {
        String sql = "SELECT * FROM user ORDER BY nameID DESC LIMIT 1";
        ResultSet res = DBQuary.query(sql);
        try {
            res.next();
            User user = createUserFromResultSet(res);
            userMap.put(user.getNameID(), user);
        } catch (SQLException e) {
            logger.warn("查询最新用户失败", e);
        }
    }
      //从结果集创建单个用户对象
    private static User createUserFromResultSet(ResultSet res) {
        try {
            int nameID = res.getInt("nameID");
            String name = res.getString("name");
            String sex = res.getString("sex");
            String password = res.getString("password");
            String phoneNum = res.getString("phone");
            String device_id = res.getString("device_id");
            Double account = res.getDouble("account");

            return new User(nameID, name, sex, password, phoneNum, device_id, account);
        } catch (SQLException e) {
            logger.error("从结果集创建用户失败", e);
            return null;
        }
    }

    /**
     * 获得所用用户对象
     * */
    public static HashMap<Integer, User> getUserMap() {
        return userMap;
    }

    //根据nameid查找该条用户信息
    public static User getUser(int nameID) {
        return userMap.get(nameID);
    }

    //根据name，password查找用户信息，返回id
    public static int getUserID(String name, String password) {
        for (User user : userMap.values()) {
            if (user.getName().equals(name) && user.getPassword().equals(password)) {
                return user.getNameID();
            }
        }
        return -1; // 如果没有找到匹配的用户，返回-1
    }

}