package View.admin;

import Serve.PowerBankModelDataSever;
import View.FatherFrame;
import View.FatherJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.List;

public class PowerBankMangerPanel extends FatherJPanel {
    private FatherFrame frame;
    private List<List<String>> fieldText = new ArrayList<>(); //储存管理员输入的信息
    private List<JTextField> textFields;
    private JTable table;
    private DefaultTableModel tableModel;
    private PowerBankModelDataSever modelDataSever = new PowerBankModelDataSever();
    private String[] columnNamess;

    public PowerBankMangerPanel(FatherFrame frame) {
        this.frame = frame;
        setLayout(new BorderLayout());

        //创建北部总面板，用于放置北部所有组件
        JPanel northPanel = new JPanel(new GridLayout(2,1));
        add(northPanel, BorderLayout.NORTH);

        // 添加数据输入面板
        JPanel inputPanel = createInputPanel();
        northPanel.add(inputPanel);

        // 添加删除按钮面板
        JPanel deletePanel = createDeletePanel();
        northPanel.add(deletePanel);

        //生成表格
        String[] columnNames = {"充电宝编号", "剩余电量", "出租状态", "品牌", "所在充电柜", "所在充电柜端口", "上下架状态"};
        columnNamess = columnNames;
        Object[][] data = new PowerBankModelDataSever().powerBankModel();

        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);

        // 设置表格单元格字体
        table.setFont(new Font("宋体", Font.BOLD, 16));
        table.setRowHeight(36);

        // 设置表格表头字体和高度
        Font headerFont = new Font("宋体", Font.BOLD, 16);
        JTableHeader tableHeader = table.getTableHeader();  //修改表头样式
        tableHeader.setFont(headerFont);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 36));

        // 设置表格模型监听器
        setupTableModelListener();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    // 创建输入面板
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(1,8));
        inputPanel.setPreferredSize(new Dimension(getWidth(), 80));
        String[] input = new String[] {
                "剩余电量:",
                "出租状态:",
                "品牌:",
                "所在充电柜:",
                "所在充电柜端口:",
                "上下架状态:"
        };
        Font font = new Font("宋体", Font.BOLD, 16);
        textFields = new ArrayList<>(); // 存储所有文本框

        for(String s : input) {
            JLabel text = new JLabel(s);
            text.setFont(font);
            inputPanel.add(text);
            JTextField textField = new JTextField(20);
            inputPanel.add(textField);
            textFields.add(textField);
        }

        JButton addButton = new JButton("添加");
        addButton.setFont(font);
        inputPanel.add(addButton);

        // 使用独立的监听器类处理添加按钮事件
        addButton.addActionListener(new AddButtonListener());

        return inputPanel;
    }

    // 创建删除按钮面板
    private JPanel createDeletePanel() {
        JPanel deletePanel = new JPanel();
        JButton deleteButton = new JButton("删除");
        Font font = new Font("宋体", Font.BOLD, 16);
        deleteButton.setFont(font);
        deletePanel.add(deleteButton);

        // 使用独立的监听器类处理删除按钮事件
        deleteButton.addActionListener(new DeleteButtonListener());

        return deletePanel;
    }

    // 设置表格模型监听器
    private void setupTableModelListener() {
        tableModel.addTableModelListener(e -> {
            // 获取事件类型和受影响的行
            int type = e.getType();
            int row = e.getFirstRow();
            Object[] rowData = new Object[tableModel.getColumnCount()]; // 获取整行数据
            // 确保事件不是点击表头行且数据发生了更新
            if (row != TableModelEvent.HEADER_ROW && type == TableModelEvent.UPDATE) {
                for (int i = 0; i < rowData.length; i++) {
                    rowData[i] = tableModel.getValueAt(row, i);
                }
                // 更新数据库
                modelDataSever.setPowerBank(rowData);
            }
        });
    }

    // 添加按钮事件监听器
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkInput()) {
                tableModel = new DefaultTableModel(addData(), columnNamess);
                table.setModel(tableModel);
                // 验证并重绘容器
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "请输入完整信息", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // 删除按钮事件监听器
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows.length > 0) {
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    // 同时,获得充电宝的id信息，删除数据库相对应的数据
                    int powerID = (int) tableModel.getValueAt(row, 0);
                    modelDataSever.deletePowerBankModelRow(powerID);
                    tableModel.removeRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(null, "请选择要删除的行", "提示", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private boolean checkInput() {
        for (JTextField textField : textFields) {
            if (textField.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private Object[][] addData() {
        List<String> lit = new ArrayList<>();
        for (JTextField field : textFields) {
            String text = field.getText().trim();
            lit.add(text);
        }
        return modelDataSever.addPowerBankModel(lit);
    }
}