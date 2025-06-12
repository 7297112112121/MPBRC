package Config;

public class ContextCaptcha {
    private CaptchaConfig captchaConfig;

    //设置配置
    public void setCaptchaConfig(CaptchaConfig captchaConfig){
        this.captchaConfig = captchaConfig;
    }
    //使用接口定义方法
    public void useCaptcha(){
        captchaConfig.getCaptcha();
    }
}
