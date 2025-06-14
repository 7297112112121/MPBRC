package Util.auth.captcha;

import Util.Rand;

public class SixNum implements CaptchaConfig {
    String SixNum = new Rand().generate(6);
    //验证6位验证码
    @Override
    public String getCaptcha() {
        return SixNum;
    }

    @Override
    public String setCaptchaRemindText() {
        return "请输入有效六位数验证码";
    }
}
