package View.admin.panel.user_message;

import Util.View.MyJPanel;
import Util.View.MyRenderingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserMessagePanel extends MyJPanel implements ActionListener {
    private final JPanel buttonPanel;       //按钮面板
    private final MyRenderingPanel rp;        //渲染面板
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
        rp = new MyRenderingPanel();
        add(rp, BorderLayout.CENTER);

        ////注册
        allUserMessage.addActionListener(this);
        usePowerBank.addActionListener(this);
        renturnPowerBank.addActionListener(this);
        timeoutPowerBank.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == allUserMessage) {
            //查看所有用户信息
            rp.update(new UserMessage_AllPanel());
        }else if (source == usePowerBank) {
            //查看未归还充电宝用户信息
            rp.update(new UserMessage_UsePowerBankPanel());
        }else if (source == renturnPowerBank) {
            //查看已归还充电宝用户信息
            rp.update(new UserMessage_RentrunPBPanel());
        }else if (source == timeoutPowerBank) {
            //查看超时未归还充电宝用户信息
            rp.update(new UserMessage_TimeoutPBPanel());
        }
    }
}
