package Serve.auth;

import Config.Global;
import Util.Rand;

import static Config.PhoneConfig.PHONE_NUM;


public class Is {

    public Is() {

    }

    public boolean isName(String name) {
        if (name.isEmpty()){
            return false;
        }
        return true;
    }

    /**
     * 判断密码是否为空，是否符合密码强度
     * @param password 输入的密码
     *
     * */
    public boolean isPassword(String password) {
        if (password.isEmpty()){
            return false;
        }
        //判断密码强度
        String chosePassword = Global.getPassword();
        if (!password.matches(chosePassword)) {
            return false;
        }
        return true;
    }

    //重复密码确认
    public boolean isConfirmPassword(String password, String ConfirmPassword) {
        //相同通过，不相同返回false
        if (!password.equals(ConfirmPassword)) {
                return false;
        }
        return true;
    }

    //判断手机号码位数
    public boolean isPhone(String phone) {
        if (!phone.matches(PHONE_NUM)) {
            return false;
        }
        return true;
    }

    //判断验证码
    public boolean isCaptcha( int number ,String captch) {
        Rand rand = new Rand();
        //生成验证码
        String captcha = rand.generate(number);
        if (captcha != captch) {
            return false;
        }
        return true;
    }

    //判断工号
    public boolean isWorkNumber(String workNumber) {
        //判断输入是否为空
        if (workNumber.isEmpty()){
            return false;
        }
        //判断输入是否符合格式
        if (!workNumber.matches(Global.getWorkID())){
            return false;
        }
        return true;
    }

}
