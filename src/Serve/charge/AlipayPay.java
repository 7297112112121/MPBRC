package Serve.charge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import DAO.pay.PayDAO;

import static DAO.pay.PayDAO.PaymentType.ALIPAY;


/**
 * 支付宝支付
 * */
public class AlipayPay implements PaymentStrategy {
    private static final Logger logger = LogManager.getLogger(PayDAO.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    @Override
    public boolean pay(int userNameID,int bank, double amount) {
        boolean fals = PayDAO.executePaymentTransaction(ALIPAY, userNameID, userNameID, bank);
        if (fals){
            logger.info(USER,"支付宝支付成功");
        }else {
            logger.info(USER,"支付宝支付失败");
        }
        return fals;
    }
}
