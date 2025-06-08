package powerBank.View;

import powerBank.ADO.DatabaseUtil;
import powerBank.Order;
import powerBank.Serve.OrderServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderPanel extends JPanel {
    private JTable orderTable;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton returnButton;
    private final OrderService orderService = new OrderServiceImpl(); // 依赖订单服务接口

    public OrderPanel() {
        setLayout(new BorderLayout());

        String[] columnNames = {"订单ID", "电源ID", "开始时间", "结束时间", "费用"};
        tableModel = new DefaultTableModel(columnNames, 0);
        orderTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(orderTable);

        refreshButton = new JButton("刷新订单");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateOrderTable();
            }
        });

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar.add(refreshButton);

        add(toolbar, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        updateOrderTable();

        // 新增归还按钮
        returnButton = new JButton("归还电源");
        returnButton.addActionListener(new ReturnOrderListener());

        JPanel toolbar1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolbar1.add(refreshButton);
        toolbar1.add(returnButton); // 添加到工具条
        add(toolbar1, BorderLayout.NORTH);
    }

    // 独立归还事件监听器
    private class ReturnOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = orderTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(OrderPanel.this, "请选择要归还的订单", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int orderId = (int) tableModel.getValueAt(selectedRow, 0);
            Order order = orderService.getOrderById(orderId);

            if (order.getEndTime() != null) {
                JOptionPane.showMessageDialog(OrderPanel.this, "该订单已结束", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 计算实际使用时长（分钟）
            long durationMinutes = (new Date().getTime() - order.getStartTime().getTime()) / (60 * 1000);
            double totalCost = calculateCost(durationMinutes); // 调用费用计算逻辑

            // 执行归还逻辑
            try {
                orderService.returnOrder(orderId, totalCost);
                JOptionPane.showMessageDialog(OrderPanel.this,
                        "归还成功！\n使用时长：" + durationMinutes + "分钟\n费用：" + totalCost + "元",
                        "成功", JOptionPane.INFORMATION_MESSAGE);
                updateOrderTable(); // 刷新订单列表
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(OrderPanel.this,
                        "归还失败：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

        // 费用计算规则：每小时1.5元，不足一小时按一小时算
        private double calculateCost(long minutes) {
            long hours = (minutes + 59)/ 60;
            return hours*1.5;
        }
    }

    private void updateOrderTable() {
        tableModel.setRowCount(0);
        List<Order> orders = DatabaseUtil.getAllOrders();
        for (Order order : orders) {
            Object[] row = {
                    order.getId(),
                    order.getPowerBankId(),
                    new Timestamp(order.getStartTime().getTime()),
                    order.getEndTime() != null? new Timestamp(order.getEndTime().getTime()) : "未结束",
                    String.format("%.2f元", order.getTotalCost())
            };
            tableModel.addRow(row);
        }
    }


}
