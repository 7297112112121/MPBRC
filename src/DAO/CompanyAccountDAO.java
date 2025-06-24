package DAO;

import Util.db.DBQuary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyAccountDAO {
    private static Logger logger = LogManager.getLogger(CompanyAccountDAO.class);
    public static double getAccount() {
        try {
            String sql = "SELECT * FROM company";
            ResultSet resultSet = DBQuary.query(sql);
            if (resultSet.next()) {
                return resultSet.getDouble("account");
            }
            return 0.0;
        } catch (SQLException e) {
            logger.error("查询公司账户失败");
            return 0.0;
        }
    }
}
