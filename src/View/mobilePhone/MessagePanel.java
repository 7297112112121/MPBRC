package View.mobilePhone;

import Util.View.MobilePhoneJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePanel extends MobilePhoneJPanel implements ActionListener{
    private  JTextArea message;
    private JButton returnHome;
    MessagePanel() {
        setLayout(new BorderLayout());
        //信息面板
        JPanel messagePanel = new JPanel();
        message = new JTextArea();
        messagePanel.add(message);
        add(messagePanel, BorderLayout.CENTER);

        //按钮面板
        JPanel buttonPanel = new JPanel();
        returnHome = new JButton();
        returnHome.setText("返回主界面");
        buttonPanel.add(returnHome);
        add(buttonPanel, BorderLayout.SOUTH);

        //注册
        returnHome.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == returnHome) {

        }
    }
}
