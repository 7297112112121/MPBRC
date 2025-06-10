package View.mobilePhone;

import Util.View.MobilePhoneJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HomePanel extends MobilePhoneJPanel implements ActionListener {
    private JButton message;
    private JButton user;
    private JButton admin;
    public HomePanel(){
        setLayout(new GridLayout(3,1));

        //信息面板
        JPanel first = new JPanel(new GridLayout(1,3)) ;
        message = new JButton("短信");
        user = new JButton("用户");
        admin = new JButton("管理员");
        first.add(message);
        first.add(user);
        first.add(admin);
        add(first);

        setVisible(true);

        //注册
        message.addActionListener(this);
        user.addActionListener(this);
        admin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == message) {

        }
        if (source == user) {

        }
        if (source == admin) {

        }
    }
}
