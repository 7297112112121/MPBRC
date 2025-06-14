package Config;

import Util.auth.captcha.ContextCaptcha;
import Util.auth.captcha.SixNum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class CaptchaGlobal {
    private static final Logger logger = LogManager.getLogger(CaptchaGlobal.class);
    //手机号码+验证码
    //当用户重新发送验证码时，若手机号码相同，覆盖原本的验证码
    private static CaptchaGlobal captchaGlobal = new CaptchaGlobal();
    private static HashMap<String, String> captchaList = new HashMap<>();
    private static ContextCaptcha contextCaptcha = new ContextCaptcha();
    private String capcha;//生成的验证码

    public CaptchaGlobal() {

    }

    //生成对应手机号验证码
    public String createCaptcha(String phone) {
        //设置验证码策略
        contextCaptcha.setCaptchaConfig(new SixNum());
        //生成验证码
        capcha = contextCaptcha.getCaptcha();
        //添加到表单
        captchaList.put(phone, capcha);
        return capcha;
    }

    //获得手机号对应的验证码
    public String getCapcha(String phone) {
        try {
            return captchaList.get(phone);
        } catch (RuntimeException e) {
            logger.error("请调用createCaptcha()方法，生成验证码");
            return null;
        }
    }
}
