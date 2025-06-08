package powerBank.View;

import powerBank.ADO.DatabaseUtil;
import powerBank.Order;
import powerBank.PowerBank;
import powerBank.Serve.PowerBankServiceImpl;
import powerBank.Serve.OrderServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class PowerBankPanel extends JPanel {

    private JList<PowerBank> powerBankList;
    private DefaultListModel<PowerBank> listModel;
    private JButton rentButton;

    private JLabel costLabel;

    private JTextField idField;
    private JTextField powerField;
    private JTextField brandField;
    private final PowerBankService service = new PowerBankServiceImpl();
    private final OrderService orderService = new OrderServiceImpl(); // 依赖接口

    public PowerBankPanel() {
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        powerBankList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(powerBankList);

        rentButton = new JButton("租赁");
        costLabel = new JLabel("");

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PowerBank selected = powerBankList.getSelectedValue();
                if (selected != null && "可租赁".equals(selected.getStatus())) { // 新增状态校验
                    // 1. 创建未结束的订单（end_time为null）
                    Order order = DatabaseUtil.createOrder(selected.getId());
                    if (order == null) {
                        JOptionPane.showMessageDialog(null, "创建订单失败", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 2. 更新电源状态为“租赁中”
                    selected.setStatus("租赁中");
                    DatabaseUtil.updatePowerBankStatus(selected);

                    // 3. 提示用户租赁开始，不提前计算费用
                    JOptionPane.showMessageDialog(null,
                            "租赁开始！\n订单ID：" + order.getId() + "\n当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(order.getStartTime()),
                            "成功", JOptionPane.INFORMATION_MESSAGE);

                    // 4. 清空费用显示（费用在归还时计算）
                    costLabel.setText("");

                } else if (selected != null) {
                    JOptionPane.showMessageDialog(null, "该电源不可租赁", "提示", JOptionPane.WARNING_MESSAGE);
                }
            }

        });

        updateAvailablePowerBanks(); // 刷新列表显示状态变更

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(rentButton, BorderLayout.SOUTH);
        panel.add(costLabel, BorderLayout.NORTH);

        add(panel);


        //新增电源按钮事件
            JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

            idField = new JTextField(8);
            powerField = new JTextField(8);
            brandField = new JTextField(15);

            addPanel.add(new JLabel("ID"));
            addPanel.add(idField);
            addPanel.add(new JLabel("电量(%):"));
            addPanel.add(powerField);
            addPanel.add(new JLabel("品牌:"));
            addPanel.add(brandField);

            JButton addButton = new JButton("新增电源");
            addButton.addActionListener(new AddPowerBankListener());
            addPanel.add(addButton);

            add(addPanel, BorderLayout.NORTH);

    }

    // 独立事件监听器类（实现解耦）
    private class AddPowerBankListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //电量输入框和品牌输入框
                int id = Integer.parseInt(idField.getText());
                double power = Double.parseDouble(powerField.getText());
                String brand = brandField.getText().trim();

                if (power < 0 || power > 100) {
                    JOptionPane.showMessageDialog(PowerBankPanel.this,
                            "电量需在0-100之间", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                PowerBank newPb = new PowerBank(id,power, brand);
                service.addPowerBank(newPb); // 调用接口方法

                clearFields(); // 清空输入框
                JOptionPane.showMessageDialog(PowerBankPanel.this, "添加成功");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(PowerBankPanel.this,
                        "请输入有效的电量数值", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(PowerBankPanel.this,
                        "添加失败：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
        private void clearFields() {
            idField.setText("");
            powerField.setText("");
            brandField.setText("");
        }

    }

    private void updateAvailablePowerBanks() {

        listModel.clear();
        List<PowerBank> allPowerBanks = DatabaseUtil.getAllPowerBanks();
        allPowerBanks.forEach(listModel::addElement);
    }



}

