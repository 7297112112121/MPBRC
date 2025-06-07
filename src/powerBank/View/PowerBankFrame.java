package powerBank.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static util.db.DataBase.getConnection;


public class PowerBankFrame extends JFrame {

    private JTextField brandTextField;

    private JTextField batteryTextField;

    private JTable powerBankTable;

    private DefaultTableModel tableModel;

    public PowerBankFrame() {
        setTitle("移动电源管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel brandLabel = new JLabel("品牌:");
        brandTextField = new JTextField();
        JLabel batteryLabel = new JLabel("剩余电量:");
        batteryTextField = new JTextField();
        JButton addButton = new JButton("上架移动电源");
        JButton removeButton = new JButton("下架移动电源");

        inputPanel.add(brandLabel);
        inputPanel.add(brandTextField);
        inputPanel.add(batteryLabel);
        inputPanel.add(batteryTextField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        // 表格区域面板
        String[] columnNames = {"ID", "品牌", "剩余电量", "是否可用"};
        tableModel = new DefaultTableModel(columnNames, 0);
        powerBankTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(powerBankTable);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // 按钮点击事件监听器
        addButton.addActionListener(new AddPowerBankListener());
        removeButton.addActionListener(new RemovePowerBankListener());

        // 初始化时加载移动电源数据
        loadPowerBanksFromDatabase();
    }

    private void loadPowerBanksFromDatabase() {
        String sql = "SELECT * FROM power_banks";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            tableModel.setRowCount(0);
            while (rs.next()) {
                int id = rs.getInt("id");
                String brand = rs.getString("brand");
                double remainingBattery = rs.getDouble("remaining_battery");
                boolean isAvailable = rs.getBoolean("is_available");
                Object[] rowData = {id, brand, remainingBattery, isAvailable};
                tableModel.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载移动电源信息失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }



    // 上架移动电源按钮监听器
    class AddPowerBankListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String brand = brandTextField.getText();
            if (brand.isEmpty()) {
                JOptionPane.showMessageDialog(PowerBankFrame.this, "品牌不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String batteryStr = batteryTextField.getText();
            if (batteryStr.isEmpty()) {
                JOptionPane.showMessageDialog(PowerBankFrame.this, "剩余电量不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double remainingBattery;
            try {
                remainingBattery = Double.parseDouble(batteryStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PowerBankFrame.this, "剩余电量格式不正确", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }
    
    // 下架移动电源按钮监听器
    class RemovePowerBankListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = powerBankTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(PowerBankFrame.this, "请选择要下架的移动电源", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }


        }
    }
}