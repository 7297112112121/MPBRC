package DAO;

import MyObject.PowerBank;
import MyObject.User;
import Util.db.DBQuary;
import Util.db.DBUpData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMessageDAO {
    private static Logger logger = LogManager.getLogger(UserMessageDAO.class);
    //查询是否有相同名字的信息存在（用户）
    public static boolean sameName(User user) {
        try {
            String sql = "SELECT * FROM user WHERE name = ?";
            String name = user.getName();
            ResultSet resultSet = DBQuary.query(sql, name);
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            logger.error("查询用户名称是否存在时出错：" + e);
            return false;
        }
    }

    //获得所有用户信息
    public static List<User> getUserList() {
        try {
            List<User> all = new ArrayList<>();
            String sql = "SELECT * FROM user";
            ResultSet resultSet = DBQuary.query(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setNameID(resultSet.getInt("nameid"));
                user.setName(resultSet.getString("name"));
                user.setSex(resultSet.getString("sex"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAccount(resultSet.getDouble("account"));
                all.add(user);
            }
            return all;
        } catch (SQLException e) {
            logger.error("获取所有用户信息详情失败",e);
            return null;
        }
    }

    //管理员添加新的用户信息
    public static void addUserM(List<User> users) {
        List<PowerBank> all = new ArrayList<>();
        String insert = "INSERT INTO user (name, sex, phone, account, password) VALUES (?,?,?,?,?)";
        for (User pb : users) {
            int upNum = DBUpData.update(insert, pb.getName(), pb.getSex(), pb.getPhone(), pb.getAccount(), "");
            if (upNum <= 0) {
                logger.warn("添加用户失败");
            } else {
                logger.info("添加用户成功");
            }
        }
    }

    //更新用户数据
    public static User updataUserMessage(int nameID) {
        String sql = "SELECT * FROM user WHERE nameid = ?";
        ResultSet resultSet = DBQuary.query(sql, nameID);
        try {
            if (resultSet.next()) {
                User user = new User();
                user.setNameID(resultSet.getInt("nameid"));
                user.setName(resultSet.getString("name"));
                user.setSex(resultSet.getString("sex"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setAccount(Double.parseDouble(resultSet.getString("account")));
                return user;
            }else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("查询用户信息时出错：" + e);
            return null;
        }
    }

    //管理员更新用户数据
    public static void updatePowerBankAllMessage(User user) {
        String sql = "UPDATE user SET name =?, sex =?, phone =?, account =? WHERE nameid =?";
        int upNum = DBUpData.update(sql, user.getName(), user.getSex(), user.getPhone(), user.getAccount(), user.getNameID());
        if (upNum <= 0)
            logger.error("修改用户信息失败");
        else logger.info("修改用户信息成功");
    }

    //删除用户数据
    public static void deleteUser(int id) {
        String sql = "DELETE FROM user WHERE nameid = ?";
        int upNum = DBUpData.update(sql, id);
        if (upNum <= 0) {
            logger.error("删除用户失败");
        } else {
            logger.info("删除用户成功");
        }
    }
}
