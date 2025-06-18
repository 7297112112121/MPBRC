package DAO;

import MyObject.PowerBankCabinet;
import Util.db.query.SimplyQueryAllForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CabinetDAO {
    private static Logger logger = LogManager.getLogger(CabinetDAO.class);
    /**
     * 获得所有存在的充电宝柜
     * */
    public List<PowerBankCabinet> getAllCabinets() {
        List<PowerBankCabinet> power = new ArrayList<>();
        SimplyQueryAllForm query = new SimplyQueryAllForm();
        ResultSet resultSet = query.query("power_banks_cabinet");
        if (resultSet == null) {
            logger.error("充电宝柜数据表中没有数据");
            return null;
        }
        try {
            while (resultSet.next()) {
                PowerBankCabinet cabinet = new PowerBankCabinet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("capacity")
                );
                power.add(cabinet);
            }
            return power;
        } catch (SQLException e) {
            logger.error("查询充电宝柜数据表出错", e);
            return null;
        }
    }
}
