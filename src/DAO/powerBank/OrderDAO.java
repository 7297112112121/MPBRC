package DAO.powerBank;

import MyObject.Order;
import MyObject.PowerBank;
import MyObject.PowerBankCabinet;
import Util.db.DBQuary;
import Util.db.DBUpData;
import View.powerBank.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderDAO implements OrderService {
    private static Logger logger = LogManager.getLogger(OrderDAO.class);
    @Override
    public Order getOrderById(int orderId) {
        return null;
    }

    //获取订单号码
    public Order getOrderIng(int nameid) {
        try {
            String sql = "SELECT * FROM orders WHERE nameid = ?";
            ResultSet resultSet = DBQuary.query(sql,nameid );
            while (resultSet.next()) {
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
                return new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price);
            }
        } catch (SQLException e) {
            logger.error("查询进行订单失败", e);
        }
        return null;
    }

    @Override
    public void returnOrder(int orderId, double totalCost) throws Exception {
        // 1. 检查订单是否存在且未结束
        Order order = getOrderById(orderId);
        if (order == null || order.getEndTime() != null) {
            throw new Exception("无效订单或已结束");
        }

        // 2. 更新订单结束时间和费用
        String sql = "UPDATE orders SET end_time = NOW(), total_cost = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, totalCost);
            stmt.setInt(2, orderId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new Exception("订单更新失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException("归还订单失败", e);
        }

        // 3. 恢复移动电源状态为“可租赁”
        PowerBank powerBank = DatabaseUtil.getPowerBankById(order.getPowerBankId());
        if (powerBank != null) {
            powerBank.setStatus("可租赁");
            DatabaseUtil.updatePowerBankStatus(powerBank);
        }
    }

    public List<Order> getAllOrders() {
        return DatabaseUtil.getAllOrders();
    }

    private Order mapToOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int powerBankId = rs.getInt("power_bank_id");
        Timestamp startTime = rs.getTimestamp("start_time");
        Timestamp endTime = rs.getTimestamp("end_time");
        double totalCost = rs.getDouble("total_cost");

        Order order = new Order(id, powerBankId, startTime);
        if (endTime != null) {
            order.setEndTime(endTime);
        }
        order.setTotalCost(totalCost);
        return order;
    }

    //向订单表添加新的订单信息
    public int addOrder(PowerBankCabinet powerBankCabinet, PowerBank powerBank, int nameid, double price) {
        String sql = "INSERT INTO orders (power_bank_id, cabinet, cabinet_powerid, nameid, price) VALUES (? , ?, ?, ?, ?)";
        return DBUpData.update(sql, powerBank.getId(), powerBankCabinet.getId(), powerBank.getPowerID(), nameid, price);
    }
}