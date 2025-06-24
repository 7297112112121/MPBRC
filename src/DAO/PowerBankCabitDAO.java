package DAO;

import MyObject.PowerBank;
import View.powerBank.PowerBankService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static Serve.PowerBank.POWER_RENTAL_ING;
import static Serve.PowerBank.POWER_RENTAL_NO;

public class PowerBankCabitDAO implements PowerBankService {
    // 管理员添加充电宝
    @Override
    public void addPowerBank(PowerBank powerBank) {
        // 定义插入移动电源的SQL语句
        String sql = "INSERT INTO power_banks (id, remaining_power, status, brand) " +
                "VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = PowerBankDAO.getConnection();
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
        return PowerBankDAO.getAllPowerBanks();
    }

    // 获取本机所有充电宝信息
    public List<PowerBank> getAvailablePowerBanks(int id) {
        // 复用DatabaseUtil原有逻辑，或添加新实现
        List<PowerBank> list =  PowerBankDAO.getAllPowerBanksOfCabint(id);
        // 过滤掉不可租凭与租凭中的充电宝
        for (PowerBank powerBank : list) {
            if (powerBank.getStatus().equals(POWER_RENTAL_NO) || powerBank.getStatus().equals(POWER_RENTAL_ING)) {
                list.remove(powerBank);
            }
        }
        return list;
    }

    @Override
    public void updatePowerBankStatus(PowerBank powerBank) {
        PowerBankDAO.updatePowerBankStatus(powerBank);
    }

}