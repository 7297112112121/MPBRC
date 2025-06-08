package admin.View.panel;

import global.view_tool.MyJPanel;
import global.view_tool.RenderingPanel;

import javax.swing.*;
import java.awt.*;

public class UserMessagePanel extends MyJPanel {
    private final JPanel buttonPanel;       //按钮面板
    private final RenderingPanel rp;        //渲染面板
    private final JButton allUserMessage;   //查看所有用户消息
    private final JButton usePowerBank;     //使用租借充电宝的用户（未归还）
    private final JButton renturnPowerBank; //已归还充电宝的用户
    private final JButton timeoutPowerBank; //超时未归还充电宝的用户

    public UserMessagePanel() {
        super();
        setLayout(new BorderLayout());
        ////按钮面板
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.NORTH);

        //查看所有用户信息
        allUserMessage = new JButton("所有用户信息");
        allUserMessage.setHorizontalTextPosition(JButton.CENTER);
        buttonPanel.add(allUserMessage);

        //使用租借充电宝的用户（未归还）
        usePowerBank = new JButton("未归还充电宝用户");
        usePowerBank.setHorizontalTextPosition(JButton.CENTER);
        buttonPanel.add(usePowerBank);

        //已归还充电宝的用户
        renturnPowerBank = new JButton("已归还充电宝用户");
        renturnPowerBank.setHorizontalTextPosition(JButton.CENTER);
        buttonPanel.add(renturnPowerBank);

        //超时未归还充电宝的用户
        timeoutPowerBank = new JButton("超时未归还充电宝的用户");
        timeoutPowerBank.setHorizontalTextPosition(JButton.CENTER);
        buttonPanel.add(timeoutPowerBank);

        ////渲染面板
        rp = new RenderingPanel();
        add(rp, BorderLayout.CENTER);
    }

}
