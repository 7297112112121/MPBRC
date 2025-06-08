package admin.View.panel.user_message;

import admin.Serve.UserMessageManager;
import global.view_tool.MyJPanel;
import user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashMap;

public class UserMessage_AllPanel extends MyJPanel implements ActionListener {
    private final JTable allUserMessageTable ;              //所有用户信息表
    private final DefaultTableModel defaultt;               //所有用户信息表模型
    private final  JButton updataButton; //手动更新用户数据（重新从数据库读取）
    private final  JButton allUpdataButton ; //启动全局修改用户数据（是可直接在表格修改，而非从数据库重新读取会弹出是否启动全局修改提示）
    public  UserMessage_AllPanel(){
        setLayout(new BorderLayout());

        //用户信息表设置
        String[] columnNames = {"ID", "昵称", "性别", "电话", "管理员", "充值余额"};
        //所有用户信息表
        JPanel userMessagePanel = new JPanel();
        defaultt = new DefaultTableModel(columnNames ,0);
        allUserMessageTable  = new JTable(defaultt);
        JScrollPane scrollPane = new JScrollPane(allUserMessageTable);
        userMessagePanel.add(scrollPane);
        add(userMessagePanel, BorderLayout.CENTER);

        //按钮
        JPanel buttonPanel = new JPanel();
        updataButton = new JButton("更新");
        allUpdataButton = new JButton("直接修改");
        buttonPanel.add(updataButton);
        buttonPanel.add(allUpdataButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //注册
        updataButton.addActionListener(this);
        allUpdataButton.addActionListener(this);

        //录入数据
        addData();

        //全局显示

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == updataButton) {
            //手动更新并查询用户数据（从数据重新读取数据）
            defaultt.setRowCount(0);
            addData();
        } else if (source == allUpdataButton) {
            //启动全局修改用户数据（不是从数据库重新读取，而是允许管理员直接在表格修改，而无需单点编辑）
        }
    }

    /**
     * 模型添加数据
     * */
    private void addData() {
        //查询所有用户数据
        Collection<User> userMessage = UserMessageManager.upDataUserAllMessageAndQueryAll();
        //将用户数据添加到表格中
        for (User user : userMessage) {
            Object[] rowData = {
                    user.getNameID(),
                    user.getName(),
                    user.getSex(),
                    user.getPhone(),
                    user.getAdminID(),
                    user.getBalance()
            };
            defaultt.addRow(rowData);
        }

    }
}
