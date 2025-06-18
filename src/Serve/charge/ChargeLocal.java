package Serve.charge;

import Util.db.insert.SimplyInsertAllForm;
import Util.db.query.SimplyQueryWhere;
import Util.db.set.SimplySet;
import MyObject.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChargeLocal {
    private Logger logger = LogManager.getLogger(ChargeLocal.class);
    public ChargeLocal() {

    }
    //充值函数
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
            account_logUpData(user);
            logger.info("充值成功,充值金额：{}", inputManey);
            return true;
        }else {
            return false;
        }
    }
    //获得账户金额变动记录函数
    public List<String> getAccountLogin(int nameID) {
        List<String> logs = new ArrayList<>();
        try {
            String ID = String.valueOf(nameID);
            ResultSet resultSet = null;
            SimplyQueryWhere queryWhere = new SimplyQueryWhere();
            resultSet = queryWhere.query(
                    "account_log",
                    "nameid", ID
            );
            String previousAccount = null;
            String currentAccount = null;
            String createTime = null;
            String allText = null;
            double difference = 0.0;

// 获取第一条记录作为基准
            if (resultSet.next()) {
                previousAccount = resultSet.getString("account");
                createTime = resultSet.getString("create_time");

                // 第一条记录没有前驱，显示初始金额
                allText = "时间：" + createTime + "   " + "金额:" + previousAccount + "   " + "变动：初始金额";
                logs.add(allText);

                // 处理后续记录，计算差值
                while (resultSet.next()) {
                    currentAccount = resultSet.getString("account");
                    createTime = resultSet.getString("create_time");

                    // 计算当前记录与前一条记录的差值
                    difference = Double.parseDouble(currentAccount) - Double.parseDouble(previousAccount);

                    // 格式化并添加到日志列表
                    allText = "时间：" + createTime + "   " + "金额:" + currentAccount + "   " + "变动：" + difference;
                    logs.add(allText);

                    // 更新前一条记录为当前记录，准备下一次计算
                    previousAccount = currentAccount;
                }
            }
            return logs;
        } catch (SQLException e) {
           logger.error("获取账户变动记录失败：{}", e.getMessage());
           return null;
        }
    }

    //更新account_log表
    private void account_logUpData( User user) {
        String nameID = String.valueOf(user.getNameID());
        String account = String.valueOf(user.getAccount());
        SimplyInsertAllForm insertAllForm = new SimplyInsertAllForm();
        insertAllForm.insert(
                "account_log",
                "nameid", "account",
                ";", nameID, account
        );
    }
}
