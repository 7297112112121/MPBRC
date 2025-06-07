package powerBank;

import powerBank.Order;
import powerBank.OrderService;

import java.sql.*;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrderById(int orderId) {
        String sql = "SELECT id, power_bank_id, start_time, end_time, total_cost " +
                "FROM orders WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapToOrder(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询订单失败", e);
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
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    @Override
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
}
