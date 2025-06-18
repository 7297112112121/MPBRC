package DAO.powerBank;

import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Util.db.DBQuary;
import Util.db.DBUpData;
import View.powerBank.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO implements OrderService {
    private static Logger logger = LogManager.getLogger(OrderDAO.class);

    @Override
    public void returnOrder(int orderId, double totalCost) throws Exception {

    }

    //获取订单号码
    public Order getOrderIng(int nameid) {
        try {
            String sql = "SELECT * FROM orders WHERE nameid = ?";
            ResultSet resultSet = DBQuary.query(sql,nameid );
            if (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp("end_time");
                //核查订单时间是否结束
                if (timestamp != null) {
                    //结束了返回null
                    return null;
                }
                //订单没有结束
                Integer id = resultSet.getInt("id");
                Integer powerBankId = resultSet.getInt("power_bank_id");
                Timestamp startTime = resultSet.getTimestamp("start_time");
                Timestamp endTime = resultSet.getTimestamp("end_time");
                Double totalCost = resultSet.getDouble("total_cost");
                Integer cabinet = resultSet.getInt("cabinet");
                Integer cabinetPowerID = resultSet.getInt("cabinet_powerid");
                Double price = resultSet.getDouble("price");
                String plan = resultSet.getString("plan");
                String status = resultSet.getString("status");
                return new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price, plan, status);
            }
        } catch (SQLException e) {
            logger.error("查询进行订单失败", e);
        }
        return null;
    }

    //获取结束的订单
    public List<Order> getOverOrders(int nameid) {
        List<Order> list = new ArrayList<>();
        try {
            String sql =  "SELECT * FROM orders WHERE `nameid` = " + nameid;
            ResultSet resultSet = DBQuary.query(sql,nameid);
            while (resultSet.next()) {
                if (resultSet.getTimestamp("end_time") != null || resultSet.getString("status").equals("已结束")) {
                    Integer id = resultSet.getInt("id");
                    Integer powerBankId = resultSet.getInt("power_bank_id");
                    Timestamp startTime = resultSet.getTimestamp("start_time");
                    Timestamp endTime = resultSet.getTimestamp("end_time");
                    Double totalCost = resultSet.getDouble("total_cost");
                    Integer cabinet = resultSet.getInt("cabinet");
                    Integer cabinetPowerID = resultSet.getInt("cabinet_powerid");
                    Double price = resultSet.getDouble("price");
                    String plan = resultSet.getString("plan");
                    String status = resultSet.getString("status");
                     list.add(new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price, plan, status));
                }
                return list;
            }
        } catch (SQLException e) {
            logger.error("查询结束订单失败", e);
            return null;
        }
        return null;
    }


    public List<Order> getAllOrders(int nameid) {
        return DatabaseUtil.getAllOrders(nameid);
    }


    //向订单表添加新的订单信息
    public int addOrder(PowerBankCabinet powerBankCabinet, PowerBank powerBank, int nameid, double price, String plan) {
        String sql = "INSERT INTO orders (power_bank_id, cabinet, cabinet_powerid, nameid, price, plan, status) VALUES (? ,?,?, ?, ?, ?, ?)";
        return DBUpData.update(sql, powerBank.getId(), powerBankCabinet.getId(), powerBank.getPowerID(), nameid, price,plan , "租借中");
    }

    //向表单填入结束时间
    public int addEndTime() {
        //获得当前系统时间
        Timestamp now = new Timestamp(new Date().getTime());
        //填入数据库orders表
        String sql = "INSERT INTO orders (end_time) VALUES (?)";
        return DBUpData.update(sql, now);
    }
}