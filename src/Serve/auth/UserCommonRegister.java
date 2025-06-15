package Serve.auth;

import DAO.insert.ContextInsert;
import DAO.insert.SimplyInsertAllForm;
import View.FatherFrame;

import javax.swing.*;

/**
 * 名字输入框、提示标签
 * 密码输入框、密码提示标签
 * 确认密码输入框、确认密码提示标签
 * 手机号输入框，手机号提示标签
 * 手机验证码，手机号提标签
 * */
public class UserCommonRegister {
    private Verify verify = new Verify();

    public boolean register(
            JTextField name, JLabel nameRimd,
            JPasswordField password, JLabel passwordRimd,
            JPasswordField comfirmPassword, JLabel comfirPasswordRimd,
            JTextField phone, JLabel phoneRimd,
            JTextField captcha, JLabel captchaRimd) {

        //清理提示标签
        clear(nameRimd, passwordRimd, comfirPasswordRimd, phoneRimd, captchaRimd);

        //输入的名字
        String nam = name.getText().trim();
        //输入的密码
        String passwor = password.getText().trim();
        //输入的重复密码
        String comfirPasswor = comfirmPassword.getText().trim();
        //输入的手机号
        String phon = phone.getText().trim();
        //输入的验证码
        String ca = captcha.getText().trim();

        boolean fals = true;    //验证开关
        int count = 0;          //记录验证不通过次数
        // 验证用户名
        if (!verify.isName(nam)) {
            // 如果用户名验证失败，设置错误提示信息
            nameRimd.setText(verify.setNameRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证密码
        if (!verify.isPassword(passwor)) {
            // 如果密码验证失败，设置错误提示信息
            passwordRimd.setText(verify.setPasswordRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证确认密码
        if (!verify.isConfirmPassword(passwor, comfirPasswor)) {
            // 如果确认密码验证失败，设置错误提示信息
            comfirPasswordRimd.setText(verify.setConfirmPasswordRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证手机号
        if (!verify.isPhone(phon)) {
            // 如果手机号验证失败，设置错误提示信息
            phoneRimd.setText(verify.setPhoneRimd());
            // 返回false，表示验证失败
            count++;
        }

        // 验证验证码
        try {
            if (!verify.isCaptcha(phon, ca)) {
                // 如果验证码验证失败，设置错误提示信息
                captchaRimd.setText(verify.setCaptchaRimd());
                // 返回false，表示验证失败
                count++;
            }
        } catch (RuntimeException e) {
            // 如果验证码验证没有生成，设置错误提示信息
            captchaRimd.setText(e.getMessage());
        }

        //如果验证不通过，则返回false
        if (count > 0) {
            return false;
        }
        //注册信息到数据库中
        ContextInsert contextInsert =new ContextInsert();
        contextInsert.setInsert(new SimplyInsertAllForm());
        contextInsert.insert(
                //要插入的表
                "user", "name", "password", "phone",
                //要写入的字段
                ";", nam, passwor, phon
        );

        //符合验证码返回true
        return true;
    }

    //密码提示信息
    public String setPasswordRemind() {
        return verify.setPasswordRimd();
    }

    //生成验证码
    public void createCaptcha(String phone,JButton phoneButton , JLabel phoneRimd, FatherFrame frame) {
        verify.createCaptchaWithUI(phone, phoneButton, phoneRimd, frame);
    }

    //获得验证码
    public String getCaptcha(String phone) {
        return verify.getCapcha(phone);
    }

    //清理标签
    private void clear(JLabel nameRimd, JLabel passwordRimd, JLabel comfirPasswordRimd, JLabel phoneRimd, JLabel captchaRimd) {
        nameRimd.setText("");
        passwordRimd.setText("");
        comfirPasswordRimd.setText("");
        phoneRimd.setText("");
        captchaRimd.setText("");
    }
}
