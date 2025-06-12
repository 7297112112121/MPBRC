package Config.strange.captcha;

import Config.CaptchaConfig;

public class Captcha_SixNum implements CaptchaConfig {
    @Override
    public String getCaptcha() {
        return SixNum;
    }
}
