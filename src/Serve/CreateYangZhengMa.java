package Serve;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class CreateYangZhengMa {
    private final static Logger logger = LogManager.getLogger(CreateYangZhengMa.class);

    /**
     * 生成指定长度的随机数字验证码
     */
    public static String verificationNum(int length) {
        if (length <= 0) {
            logger.warn("验证码长度必须大于0");
            return null;
        }

        Random random = new Random();
        StringBuilder code = new StringBuilder();

        // 确保第一位不为0
        code.append(random.nextInt(9) + 1);

        // 生成剩余的数字
        for (int i = 1; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

}