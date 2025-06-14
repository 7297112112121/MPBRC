package Util.auth;

import Config.CaptchaGlobal;
import Config.Global;

import static Util.auth.phone.PhoneConfig.PHONE_NUM;


public class Verify {

    public Verify() {

    }

    public boolean setName(String name) {
        if (name.isEmpty()){
            return false;
        }
        return true;
    }
    //获得用户名提示信息
    public String setNameRimd() {
        return "用户名不能为空";
    }

    /**
     * 判断密码是否为空，是否符合密码强度
     * @param password 输入的密码
     *
     * */
    public boolean setPassword(String password) {
        if (password.isEmpty()){
            return false;
        }
        //判断密码强度
        String chosePassword = Global.getPasswordRegular();
        if (!password.matches(chosePassword)) {
            return false;
        }
        return true;
    }
    //获得密码提示信息
    public String setPasswordRimd() {
        String chosePassword = Global.getPasswordRemind();
        return chosePassword;
    }

    //重复密码确认
    public boolean setConfirmPassword(String password, String ConfirmPassword) {
        //相同通过，不相同返回false
        if (!password.equals(ConfirmPassword)) {
            return false;
        }
        return true;
    }
    //获得重复密码提示信息
    public String setConfirmPasswordRimd() {
        return "两次密码不一致";
    }

    //判断手机号码位数
    public boolean setPhone(String phone) {
        if (!phone.matches(PHONE_NUM)) {
            return false;
        }
        return true;
    }
    //获得手机号码提示信息
    public String setPhoneRimd() {
        return "请填写11位有效手机号码";
    }

    /**
     * 生成验证码
     * */
    public String createCaptcha(String phone) {
        CaptchaGlobal captchaGlobal = new CaptchaGlobal();
        return captchaGlobal.createCaptcha(phone);
    }

    /**
     * 获得验证码
     * */
    public String getCapcha(String phone) {
        CaptchaGlobal captchaGlobal = new CaptchaGlobal();
        return captchaGlobal.getCapcha(phone);
    }

    //判断验证码
    /**
     * @param phone 用户手机号码
     * @param captcha 用户输入的验证码
     * */
    public boolean setCaptcha( String phone, String captcha) throws RuntimeException{
        //判断验证码是否为空
        if (captcha.isEmpty()){
            throw new RuntimeException("未发送验证码");
        }
        CaptchaGlobal captchaGlobal = new CaptchaGlobal();
        if (captcha != captchaGlobal.getCapcha(phone)) {
            return false;
        }
        return true;
    }
    //获得验证码提示信息
    public String setCaptchaRimd() {
        return "验证码错误";
    }

    //判断工号
    public boolean setWorkNumber(String workNumber) {
        //判断输入是否为空
        if (workNumber.isEmpty()){
            return false;
        }
        //判断输入是否符合格式
        if (!workNumber.matches(Global.getWorkIDRegular())){
            return false;
        }
        return true;
    }
    //获得工号提示信息
    public String setWorkNumberRimd() {
        return "工号格式错误";
    }

}
