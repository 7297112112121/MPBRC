package DAO.powerBank;

import MyObject.Order;
import MyObject.PowerBank;
import Util.TimeSystem;
import Util.db.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final Logger logger = LogManager.getLogger(DatabaseUtil.class);

    public static Connection getConnection() throws SQLException {
        return DataBase.getConnection();
    }

    // 获取所有可用移动电源
    public static List<PowerBank> getAllPowerBanks() {
        List<PowerBank> all = new ArrayList<>();
        String sql = "SELECT id, remaining_power, status, brand FROM power_banks ";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                double remainingPower = rs.getDouble("remaining_power");
                String status = rs.getString("status");
                String brand = rs.getString("brand");
                PowerBank pb = new PowerBank(id,remainingPower, brand);
                pb.setStatus(status);
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
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int powerBankId = rs.getInt("id");
                    double remainingPower = rs.getDouble("remaining_power");
                    String status = rs.getString("status");
                    String brand = rs.getString("brand");
                    PowerBank pb = new PowerBank(id, remainingPower, brand);
                    pb.setStatus(status);
                    return pb;
                }
            }
        } catch (SQLException e) {
            logger.error("获取移动电源详情失败", e);
        }
        return null;
    }

    // 更新移动电源状态
    public static void updatePowerBankStatus(PowerBank powerBank) {
        String sql = "UPDATE power_banks SET status =? WHERE id =?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, powerBank.getStatus());
            stmt.setInt(2, powerBank.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("更新移动电源状态失败", e);
        }
    }

    public static void addPowerBank(int id, double remainingPower, String brand) {
        String sql = "INSERT INTO power_banks (id, remaining_power, status, brand) VALUES (?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setDouble(2, remainingPower);
            stmt.setString(3, remainingPower > 50? "可租赁" : "不可租赁");
            stmt.setString(4, brand);
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.error("添加移动电源失败", e);
        }
    }

    // 创建订单
    public static Order createOrder(int powerBankId) {
        String sql = "INSERT INTO orders (power_bank_id, start_time) VALUES (?, NOW())";
        int orderId = -1;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, powerBankId);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error("创建订单失败", e);
        }
        if (orderId != -1) {
            return new Order(orderId, powerBankId, new java.util.Date());
        }
        return null;
    }

    // 更新订单结束时间和费用
    public static void updateOrder(Order order, double totalCost) {
        String sql = "UPDATE orders SET end_time = NOW(), total_cost =? WHERE id =?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, totalCost);
            stmt.setInt(2, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取所有订单
    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT id, power_bank_id, start_time, end_time, total_cost FROM orders";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int powerBankId = rs.getInt("power_bank_id");
                java.sql.Timestamp startTime = rs.getTimestamp("start_time");
                java.sql.Timestamp endTime = rs.getTimestamp("end_time");
                double totalCost = rs.getDouble("total_cost");

                Order order = new Order(id, powerBankId, startTime);
                if (endTime != null) {
                    order.setEndTime(endTime);
                }
                order.setTotalCost(totalCost);
                orders.add(order);
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
            order.setEndTime(new java.util.Date());
            order.setTotalCost(order.calculateCost()); // 计算费用

            String sql = "UPDATE orders SET end_time = ?, total_cost = ? WHERE id = ?";
            try (Connection conn = getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
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
            }
        } catch (SQLException e) {
            logger.error("获取订单详情失败", e);
        }
        return null;
    }
}