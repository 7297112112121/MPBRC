package Serve.auth.captcha;

public interface CaptchaConfig {

    /// 获得验证码
    String getCaptcha();

    //获得验证码提示文本
    String setCaptchaRemindText();

    //设置倒计时时间
    int setCountDown();

}
