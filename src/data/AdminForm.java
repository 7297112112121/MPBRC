package data;

import admin.Admin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import global.db.DBQuary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class AdminForm {
    private static HashMap<Integer, Admin> adminMap = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(AdminForm.class);

    public static HashMap<Integer, Admin> getAdminMap() {
        return adminMap;
    }

    /**
     * 获得数据库中所有管理员对象
     **/

    public static void loadAdmins() {
        try {
            String sql = "SELECT * FROM admin";
            ResultSet res = DBQuary.query(sql);

            adminMap.clear();

            while (res.next()) {
                Admin admin = new Admin();
                int ID = res.getInt("id");
                String name = res.getString("name");
                String password = res.getString("password");
                String phone = res.getString("phone");
                String workid = res.getString("workid");
                admin.setID(ID);
                admin.setName(name);
                admin.setPassword(password);
                admin.setPhone(phone);
                admin.setWorkID(workid);
                adminMap.put(ID, admin);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     * 加载最新的管理员数据
     * */
    public static void loadNewAdmins() {
        String sql = "SELECT * FROM admin ORDER BY create_time DESC LIMIT 1";
        ResultSet res = DBQuary.query(sql);
        try {
            res.next();
            Admin admin = new Admin();
            admin.setID(res.getInt("id"));
            admin.setName(res.getString("name"));
            admin.setPassword(res.getString("password"));
            admin.setPhone(res.getString("phone"));
            admin.setWorkID(res.getString("workid"));
            adminMap.put(admin.getID(), admin);
        } catch (SQLException e) {
            logger.warn("查询最新管理员失败", e);
        }
    }

    /**
     * 获得所有用户数据
     * */
    //返回hashmap集合
    public HashMap<Integer, Admin> getAdminsHashMap(){
        return adminMap;
    }

        /**
         * 查找管理员对象
         * 根据id查询有无该管理员
         * */
        public static Admin getAdmin (int id){
            if (adminMap.get(id) != null) {
                return adminMap.get(id);
            }
            return null;
        }

        /**
         * 查找管理员名字
         * */
        //根据名字查名字（主要用于确认管理员名称信息）
        public static String queryName (String name){
            for (Admin admin : adminMap.values()) {
                if (admin.getName().equals(name)){
                    return admin.getName();
                }
            }
            //没找到返回null
            return null;
        }

        //根据id查找名字
        public static String queryName (int id){
            if (adminMap.get(id) != null) {
                return adminMap.get(id).getName();
            }
            //没找到返回null
            return null;
        }

        /**
         * 查找管理员id
         * */
        //根据名字、密码查找管理员id
        public static int getAdminID (String name, String password){
            for (Admin admin : adminMap.values()) {
                if (admin.getName().equals(name) && admin.getPassword().equals(password))
                    return admin.getID();
            }
                return -1;
        }

}
