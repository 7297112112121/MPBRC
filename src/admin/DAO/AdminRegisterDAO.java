package admin.DAO;

import admin.Admin;
import data.AdminForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import global.db.DBQuary;
import global.db.DBUpData;
import global.erro.AdminObjectException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 注册业务
 * */
public class AdminRegisterDAO implements  AdminSQL {
    private static final Logger logger = LogManager.getLogger(AdminRegisterDAO.class);
    /**
     * 执行用户注册，主要处理注册异常
     * 默认注册失败，返回false
     * */
    public static boolean register(Object obj) throws AdminObjectException {
        try {
            boolean fals = false;
            Admin admin = (Admin) obj;
            String sql = "SELECT * FROM admin WHERE name = ? AND password = ?";
            ResultSet rs = DBQuary.query(sql,admin.getName(), admin.getPassword());
            if (rs.next()) {
                throw new AdminObjectException("该管理员已存在");
            }

            //写入mysql数据库
            String sql1 = "INSERT INTO admin(name, password, phone, workid) VALUES(?, ?, ?, ?)";
            int success = DBUpData.update(sql1, admin.getName(), admin.getPassword(), admin.getPhone(), admin.getWorkID());

            //写入程序数据库
            AdminForm.loadNewAdmins();

            return true;
        }catch (SQLException e){
            logger.warn("检查sql语句 或 占位符数量是否对等。",e);
            return false;
        }
    }
}
