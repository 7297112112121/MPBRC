package user.Service.register;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.DAO.QueryMessage;
import user.DAO.RegisterDAO;
import user.User;
import util.erro.*;
import util.time.PhoneCountdown;
import util.tset.UserPhoneMessage;

import javax.swing.*;

import static data.UserForm.loadNewUsers;
import static util.config.PasswordConfig.*;

public class RegisterServe {
    private static Logger logger = LogManager.getLogger(RegisterServe.class);
    private static final Marker USER = MarkerManager.getMarker("USER");

    private static String number;       //储存手机验证码

    /**
     * 用户名验证
     * */
    public static void isName(String name) throws SetPasswordExcetion {
        if (name.isEmpty()) {
            throw new NameException("用户名不能为空");
        }
        String nameB = QueryMessage.nameQuery(name);
        if (nameB != null) {
            throw new NameException("用户名已存在");
        }
    }

    /**
     * 确认密码
     * */
    public static boolean isPassword(String password, String rePassword) throws SetPasswordExcetion {
        boolean fals = false;
        if (!password.matches(PWD_B)) {
            throw new SetPasswordExcetion(PWD_B_TEXT);
        }
        if (!password.equals(rePassword)) {
            throw new QueryPasswordException("两次密码不一致");
        }
        fals = true;
        return false;
    }

    public static void isPhone(String phone) throws PhoneException {
        if (!phone.matches(PHONE_NUM)) {
            throw new PhoneException(PHONE_TEXT);
        }
    }

    /**
     * 确认验证码
     * */
    public static void isYangZhengMa(String yangZhengMa) throws PhoneException {
        if (!yangZhengMa.equals(number)) {
            throw new PhoneException("验证码错误");
        }
    }

    /**
     * 注册
     * */
    public static void register(String name, String phone, String password) throws UserObjectException {
        User user = new User(name, phone, password);
        //访问数据访问层
        boolean flas = RegisterDAO.register(user);
        if (!flas) {
            throw new UserObjectException("注册失败");
        }
        //刷新用户列表
        loadNewUsers();
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
        logger.info(USER,"发送验证码成功: {}", number);
        //启动计时启，60s后重新设置文字为：发送验证码
        new PhoneCountdown().createCountdown(time,button);
        return number;
    }

}
