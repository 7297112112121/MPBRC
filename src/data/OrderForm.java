package data;

import admin.Admin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import powerBank.Order;
import util.db.DBQuary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class OrderForm {
    /**
     * 键：订单的ID
     * 值: 订单的对象
     * */
    private static HashMap<Integer, Order> orderMap = new HashMap<>();
    private static final Logger logger = LogManager.getLogger(AdminForm.class);

    public static void loadOrders() {
        try{
            String sql =  "SELECT * FROM orders";
            ResultSet res = DBQuary.query(sql);

            orderMap.clear();

            while (res.next()) {
                Order order = new Order();
                int id = res.getInt("id");
                int powerBankID = res.getInt("power_bank_id");
                Date startTime = res.getDate("start_time");
                Date endtime  =res.getDate("end_time");
                order.setId(id);
                order.setPowerBankId(powerBankID);
                order.setStartTime(startTime);
                order.setEndTime(endtime);
                orderMap.put(id, order);



            }
        }catch (SQLException e) {
            logger.error("加载订单失败", e);
        }
    }


}
