package user.DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import global.db.DBQuary;
import global.db.DBUpData;
import global.db.DataBase;
import global.erro.PayException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 支付方式
 */
public class PaymentTransaction {
    private static final Logger logger = LogManager.getLogger(PaymentTransaction.class);
    private static final Marker USER = MarkerManager.getMarker("USER");


    public enum PaymentType {
        WECHAT, ALIPAY
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
            String toTableName = getTableName(paymentType);

            //检查用户账户余额
            String checkFromAccountSql = "SELECT balance FROM " + fromTableName + " WHERE nameid =?";
            ResultSet userAccount = DBQuary.query(checkFromAccountSql, fromnameId);
            double balance = userAccount.getDouble("balance");
            if (balance < amount) {
                throw new RuntimeException("账户余额不足");
            }

            // 转出账户扣钱
            String updateFromAccountSql = "UPDATE " + fromTableName + " SET balance = balance -? WHERE nameid =?";
            int rowsAffectedFrom = DBUpData.update(updateFromAccountSql, amount, fromnameId);

            // 转入账户加钱
            String updateToAccountSql = "UPDATE " + toTableName + " SET balance = balance +? WHERE nameid =?";
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
        } catch (PayException pay) {
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
            default:
                throw new IllegalArgumentException("不支持的支付类型: " + paymentType);
        }
    }


}