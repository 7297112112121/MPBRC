package admin.Serve;

import admin.Admin;
import admin.DAO.RegisterDAO;
import data.AdminForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.erro.NameException;
import util.erro.PhoneException;
import util.erro.SetPasswordExcetion;

public class RegisterServe {
    private static final Logger logger = LogManager.getLogger(RegisterServe.class);
    public static void isName(String name) {
        if (name.isEmpty()) {
            throw new NameException("名字不能为空");
        }
        if (name.equals((AdminForm.queryName(name)))) {
            throw new NameException("名字已存在");
        }
    }

    public static void isPassword(String password) {
        if (password.isEmpty()) {
            throw new SetPasswordExcetion("密码不能为空");
        }
    }

    public static void isPhone(String phone) {
        if (phone.isEmpty()) {
            throw new PhoneException("手机号不能为空");
        }
    }

    public static void isWorkID(String workID) {
        if (workID.isEmpty()) {
            throw new NameException("工号不能为空");
        }
    }

    public static void register(Admin admin) {
        //写入数据库
        boolean follow = RegisterDAO.register(admin);
        if (follow) {
            //存入程序数据库
            AdminForm.loadNewAdmins();
            logger.info("{} - 注册成功",admin.getName());
        } else {
            logger.warn("注册失败");
        }
    }

}
