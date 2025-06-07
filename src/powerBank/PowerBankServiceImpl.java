package powerbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PowerBankServiceImpl implements PowerBankService {
    @Override
    public void addPowerBank(PowerBank powerBank) {
        String sql = "INSERT INTO power_banks (id, remaining_power, status, brand) " +
                "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, powerBank.getId());
            stmt.setDouble(2, powerBank.getRemainingPower());
            stmt.setString(3, powerBank.getStatus());
            stmt.setString(4, powerBank.getBrand());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("添加移动电源失败", e);
        }
    }

    @Override
    public List<PowerBank> getAvailablePowerBanks() {
        // 复用DatabaseUtil原有逻辑，或添加新实现
        return DatabaseUtil.getAllPowerBanks();
    }

    @Override
    public void updatePowerBankStatus(PowerBank powerBank) {
        DatabaseUtil.updatePowerBankStatus(powerBank);
    }
}