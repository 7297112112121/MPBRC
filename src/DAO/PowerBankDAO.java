package DAO;

import MyObject.Order;
import MyObject.PowerBank;
import Util.db.DBQuary;
import Util.db.DBUpData;
import Util.db.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PowerBankDAO {
    private static final Logger logger = LogManager.getLogger(PowerBankDAO.class);

    public static Connection getConnection() throws SQLException {
        return DataBase.getConnection();
    }

    // 获取所有可用移动电源
    public static List<PowerBank> getAllPowerBanks() {
        List<PowerBank> all = new ArrayList<>();
        String sql = "SELECT id, remaining_power, status, brand FROM power_banks ";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double remainingPower = rs.getDouble("remaining_power");
                String status = rs.getString("status");
                String brand = rs.getString("brand");
                PowerBank pb = new PowerBank(id, remainingPower, brand);
                pb.setStatus(status);
                all.add(pb);
            }
        } catch (SQLException e) {
            logger.error("获取所有可用移动电源失败", e);
        }
        return all;
    }

    // 获取本机所有可用移动电源
    public static List<PowerBank> getAllPowerBanksOfCabint(int id) {
        List<PowerBank> all = new ArrayList<>();
        String sql = "SELECT * FROM power_banks WHERE power_bank_cabinet =" + id;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int ids = rs.getInt("id");
                double remainingPower = rs.getDouble("remaining_power");
                String status = rs.getString("status");
                String brand = rs.getString("brand");
                int cabinet = rs.getInt("power_bank_cabinet");
                int portid = rs.getInt("portid");
                PowerBank pb = new PowerBank(portid, cabinet, status, remainingPower, brand, ids);
                all.add(pb);
            }
        } catch (SQLException e) {
            logger.error("获取所有可用移动电源失败", e);
        }
        return all;
    }

    // 获取移动电源详情
    public static PowerBank getPowerBankById(int id) {
        String sql = "SELECT id, remaining_power, status, brand FROM power_banks WHERE id =?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int powerBankId = rs.getInt("id");
                double remainingPower = rs.getDouble("remaining_power");
                String status = rs.getString("status");
                String brand = rs.getString("brand");
                PowerBank pb = new PowerBank(id, remainingPower, brand);
                pb.setStatus(status);
                return pb;
            }
        } catch (SQLException e) {
            logger.error("获取移动电源详情失败", e);
        }
        return null;
    }



    //获得充电宝当前状态
    public static PowerBank getNowPowerBank(int id) {
        try {
            String sql = "SELECT * FROM power_banks WHERE id =?";
            ResultSet resultSet = DBQuary.query(sql, id);
            PowerBank pb = null;
            if (resultSet.next()) {
                int powerBankId = resultSet.getInt("id");
                double remainingPower = resultSet.getDouble("remaining_power");
                String status = resultSet.getString("status");
                String brand = resultSet.getString("brand");
                pb = new PowerBank(powerBankId, remainingPower, brand);
            }
            return pb;
        } catch (SQLException e) {
            logger.error("获取移动电源详情失败", e);
            return null;
        }
    }
    // 更新移动电源租凭状态
    public static void updatePowerBankStatus(PowerBank powerBank) {
        String sql = "UPDATE power_banks SET status =? WHERE id =?";
        int upNum = DBUpData.update(sql,  powerBank.getStatus(), powerBank.getId());
        if (upNum <= 0) {
            logger.error("修改充电电源租凭状态失败");
        }
    }
    //更新电源全部信息
    public static void updatePowerBankAllMessage(PowerBank powerBank) {
        String sql = "UPDATE power_banks SET remaining_power =?, status =?, brand =?, power_bank_cabinet =?, portid =? WHERE id =?";
        int upNum = DBUpData.update(sql, powerBank.getRemainingPower(), powerBank.getStatus(), powerBank.getBrand(), powerBank.getCabinetID(), powerBank.getPowerID(), powerBank.getId());
        if (upNum <= 0)
            logger.error("修改充电电源租凭状态失败");
    }

    // 创建订单
    public static Order createOrder(int powerBankId) {
        String sql = "INSERT INTO orders (power_bank_id, start_time) VALUES (?, NOW())";
        int orderId = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet generatedKeys = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, powerBankId);
            stmt.executeUpdate();
            generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("创建订单失败", e);
        }
        if (orderId != -1) {
            return new Order(orderId, powerBankId, new Timestamp(System.currentTimeMillis()));
        }
        return null;
    }

    // 更新订单结束时间和费用
    public static void updateOrder(Order order, double totalCost) {
        String sql = "UPDATE orders SET end_time = NOW(), total_cost =? WHERE id =?";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setDouble(1, totalCost);
            stmt.setInt(2, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取所有订单
    public static List<Order> getAllOrders(int nameid) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE `nameid` = " + nameid;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Integer powerBankId = rs.getInt("power_bank_id");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                Double totalCost = rs.getDouble("total_cost");
                Integer cabinet = rs.getInt("cabinet");
                Integer cabinetPowerID = rs.getInt("cabinet_powerid");
                Double price = rs.getDouble("price");
                String plan = rs.getString("plan");
                orders.add(new Order(id, powerBankId, startTime, endTime, totalCost, cabinet, cabinetPowerID, price, plan));
            }
        } catch (SQLException e) {
            logger.error("获取所有订单失败", e);
        }
        return orders;
    }

    // 结束订单（归还时调用）
    public static void completeOrder(int orderId) {
        Order order = getOrderById(orderId);
        if (order != null && order.getEndTime() == null) { // 确保订单未结束
            order.setEndTime(new Timestamp(System.currentTimeMillis()));
            order.setTotalCost(order.calculateCost()); // 计算费用

            String sql = "UPDATE orders SET end_time = ?, total_cost = ? WHERE id = ?";
            Connection conn = null;
            PreparedStatement stmt = null;
            try {
                conn = getConnection();
                stmt = conn.prepareStatement(sql);
                stmt.setTimestamp(1, new java.sql.Timestamp(order.getEndTime().getTime()));
                stmt.setDouble(2, order.getTotalCost());
                stmt.setInt(3, order.getId());
                stmt.executeUpdate();
            } catch (SQLException e) {
                logger.error("更新订单失败", e);
            }
        }
    }

    // 获取订单详情（新增方法）
    public static Order getOrderById(int id) {
        String sql = "SELECT id, power_bank_id, start_time, end_time, total_cost FROM orders WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("id");
                int powerBankId = rs.getInt("power_bank_id");
                java.sql.Timestamp startTime = rs.getTimestamp("start_time");
                java.sql.Timestamp endTime = rs.getTimestamp("end_time");
                double totalCost = rs.getDouble("total_cost");

                Order order = new Order(orderId, powerBankId, startTime);
                if (endTime != null) {
                    order.setEndTime(endTime);
                }
                order.setTotalCost(totalCost);
                return order;
            }
        } catch (SQLException e) {
            logger.error("获取订单详情失败", e);
        }
        return null;
    }
}