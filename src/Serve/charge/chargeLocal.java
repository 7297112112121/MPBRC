package Serve.charge;

import DAO.set.SimplySet;
import MyObject.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class chargeLocal {
    private Logger logger = LogManager.getLogger(chargeLocal.class);
    public chargeLocal() {

    }
    public boolean recharge(double inputManey, int namID, User user) {
        String nameID = String.valueOf(namID);
        double money = inputManey;
        double OrMoney = user.getAccount();
        double sum = money + OrMoney;
        String sumText = String.valueOf(sum);
        SimplySet set = new SimplySet();
        int number = set.set(
          "user",
          "account", sumText,
          ";",
          "nameid",
          nameID
        );
        if (number > 0) {

            user.setAccount(sum);
            logger.info("充值成功,充值金额：{}", inputManey);
            return true;
        }else {
            return false;
        }
    }
}
