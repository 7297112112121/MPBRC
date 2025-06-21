package DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import Util.db.DBQuary;
import Util.db.DBUpData;
import Util.db.DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 支付方式
 */
public class PayDAO {
    private static final Logger logger = LogManager.getLogger(PayDAO.class);
    private static final Marker USER = MarkerManager.getMarker("USER");


    public enum PaymentType {
        WECHAT, ALIPAY, LOCAL
    }
    /**
     * 执行支付事务
     * @param paymentType 支付类型（微信或支付宝）
     * @param fromnameId 转出账户id
     * @param tonameId 转入账户id
     * @param amount 金额
     * @return 是否成功
     */

    public static boolean executePaymentTransaction(PaymentType paymentType, int fromnameId, int tonameId, double amount) {
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
            // 关闭自动提交，开启事务
            connection.setAutoCommit(false);

            // 根据支付方式选择对应的表
            String fromTableName = getTableName(paymentType);
            String toTableName = "company";

            //检查用户账户余额
            String checkFromAccountSql = "SELECT account FROM " + fromTableName + " WHERE nameid =?";
            ResultSet userAccount = DBQuary.query(checkFromAccountSql, fromnameId);
            double balance = 0;
            if (userAccount.next()) {
                balance = userAccount.getDouble("account");
            } else {
                throw new RuntimeException("没有该账户数据");
            }
            if (balance < amount) {
                throw new RuntimeException("账户余额不足");
            }

            // 转出账户扣钱
            String updateFromAccountSql = "UPDATE " + fromTableName + " SET account = account -? WHERE nameid =?";
            int rowsAffectedFrom = DBUpData.update(updateFromAccountSql, amount, fromnameId);

            // 转入账户加钱
            String updateToAccountSql = "UPDATE " + toTableName + " SET account = account +? WHERE account_id =?";
            int rowsAffectedTo = DBUpData.update(updateToAccountSql, amount, tonameId);

            // 判断支付是否成功
            if (rowsAffectedFrom > 0 && rowsAffectedTo > 0) {
                // 提交事务
                connection.commit();
                return true;
            } else {
                // 回滚事务
                connection.rollback();
                return false;
            }
        } catch (RuntimeException pay) {
            logger.info(USER, "支付失败 用户id：{}", fromnameId);
            return false;
        } catch (SQLException e) {
            // 发生异常，回滚事务
            logger.error("支付事务执行失败", e);
            return false;
        }finally {
            //重新启用事务提交
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.warn("关闭自动提交失败", e);
            }
        }
    }

    /**
     * 根据支付类型获取对应的表名
     */
    protected static String getTableName(PaymentType paymentType) {
        switch (paymentType) {
            case WECHAT:
                return "wechat_accounts"; // 微信账户表名
            case ALIPAY:
                return "alipay_accounts"; // 支付宝账户表名
            case LOCAL:
                return "user";// 用户本地账户表名
            default:
                throw new IllegalArgumentException("不支持的支付类型: " + paymentType);
        }
    }


}