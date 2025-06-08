package admin.View.panel.user_message;

import admin.Serve.UserMessageManager;
import global.view_tool.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMessage_AllPanel extends MyJPanel implements ActionListener {
    private final JTable allUserMessageTable ;              //所有用户信息表
    private final  JButton updataButton; //手动更新用户数据（重新从数据库读取）
    private final  JButton allUpdataButton ; //启动全局修改用户数据（是可直接在表格修改，而非从数据库重新读取会弹出是否启动全局修改提示）
    public  UserMessage_AllPanel(){
        setLayout(new BorderLayout());

        //所有用户信息表
        JPanel userMessagePanel = new JPanel();
        allUserMessageTable  = new JTable();
        userMessagePanel.add(allUserMessageTable);
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

        //全局显示
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == updataButton) {
            //手动更新并查询用户数据（从数据重新读取数据）
            UserMessageManager.upDataUserAllMessageAndQueryAll();
        } else if (source == allUpdataButton) {
            //启动全局修改用户数据（不是从数据库重新读取，而是允许管理员直接在表格修改，而无需单点编辑）
        }
    }
}
