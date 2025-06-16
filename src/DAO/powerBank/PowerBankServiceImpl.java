package DAO.powerBank;

import MyObject.PowerBank;
import View.powerBank.PowerBankService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PowerBankServiceImpl implements PowerBankService {
    // 管理员添加充电宝
    @Override
    public void addPowerBank(PowerBank powerBank) {
        // 定义插入移动电源的SQL语句
        String sql = "INSERT INTO power_banks (id, remaining_power, status, brand) " +
                "VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            // 设置SQL语句中的参数
            stmt.setInt(1, powerBank.getId());
            stmt.setDouble(2, powerBank.getRemainingPower());
            stmt.setString(3, powerBank.getStatus());
            stmt.setString(4, powerBank.getBrand());
            // 执行SQL语句
            stmt.executeUpdate();
        } catch (SQLException e) {
            // 抛出运行时异常
            throw new RuntimeException("添加移动电源失败", e);
        }
    }

    // 获取所有充电宝信息
    @Override
    public List<PowerBank> getAvailablePowerBanks() {
        // 复用DatabaseUtil原有逻辑，或添加新实现
        return DatabaseUtil.getAllPowerBanks();
    }

    // 获取本机所有充电宝信息
    public List<PowerBank> getAvailablePowerBanks(int id) {
        // 复用DatabaseUtil原有逻辑，或添加新实现
        return DatabaseUtil.getAllPowerBanks(id);
    }

    @Override
    public void updatePowerBankStatus(PowerBank powerBank) {
        DatabaseUtil.updatePowerBankStatus(powerBank);
    }
}