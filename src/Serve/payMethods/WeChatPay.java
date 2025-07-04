package Serve.payMethods;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import DAO.PayDAO;

import static DAO.PayDAO.PaymentType.WECHAT;

/**
 * 微信支付
 * */
public class WeChatPay implements PaymentStrategy {
    private static final Logger logger = LogManager.getLogger(PayDAO.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    @Override
    public boolean pay(int userNameID,int bank, double amount) {
        boolean fals = PayDAO.executePaymentTransaction(WECHAT, userNameID, userNameID, bank);
        if (fals){
            logger.info(USER,"支付宝支付成功");
        }else {
            logger.info(USER,"支付宝支付失败");
        }
        return fals;
    }
}
