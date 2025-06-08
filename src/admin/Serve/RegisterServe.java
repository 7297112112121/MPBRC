package admin.Serve;

import admin.Admin;
import admin.DAO.RegisterDAO;
import data.AdminForm;
import global.PasswordConfig;
import global.erro.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.Service.CreateYangZhengMa;
import global.time.PhoneCountdown;
import global.tset.UserPhoneMessage;

import javax.swing.*;

public class RegisterServe implements PasswordConfig {
    private static final Logger logger = LogManager.getLogger(RegisterServe.class);
    private static final Marker ADMIN = MarkerManager.getMarker("ADMIN");

    private static String number;
    public static void isName(String name) {
        if (name.isEmpty()) {
            throw new NameException("名字不能为空");
        }
        if (name.equals((AdminForm.queryName(name)))) {
            throw new NameException("名字已存在");
        }
    }

    /// 无需再次确认密码验证
    public static void isPassword(String password) {
        if (password.isEmpty()) {
            throw new SetPasswordExcetion("密码不能为空");
        }
        //密码强度验证
        if (password.matches(PWD_B_TEXT)) {
            throw new SetPasswordExcetion(PWD_B_TEXT);
        }
    }

    /// 需再次确认密码验证
    public static void isPassword(String password, String password2) {
        if (password.isEmpty()) {
            throw new SetPasswordExcetion("密码不能为空");
        }
        //密码强度验证
        if (password.matches(PWD_B_TEXT)) {
            throw new SetPasswordExcetion(PWD_B_TEXT);
        }
        if (!password.equals(password2)) {
            throw new QueryPasswordException("两次密码不一致");
        }
    }

    public static void isPhone(String phone) {
        if (phone.isEmpty()) {
            throw new PhoneException("手机号不能为空");
        }
    }

    public static void isYangZhengMa(String yzm) {
        if (yzm.isEmpty()) {
            throw new YangZhengMaException("验证码不能为空");
        }
        if (!yzm.equals(number)) {
            throw new YangZhengMaException("验证码错误");
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
            logger.info("{} - 注册成功",admin.getName());
        } else {
            logger.warn("注册失败");
        }
    }

    /**
     * 发送验证码
     * */
    public static String sendYangZhenMa(int time, JButton button) {
        button.setText("发送中...");
        //发送验证码
        number = CreateYangZhengMa.verificationNum(6);
        //向用户手机发送短信
        UserPhoneMessage.getUserPhoneMessage().getYangZhengMa(number);
        logger.info(ADMIN,"发送验证码成功: {}", number);
        //启动计时启，60s后重新设置文字为：发送验证码
        new PhoneCountdown().createCountdown(time,button);
        return number;
    }
}
