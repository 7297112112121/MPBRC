package View.admin.panel;

import View.admin.AdminJFrameConfig;
import View.admin.panel.order.OrderMessagePanel;
import View.admin.panel.powerBank.PowerBankMessagePanel;
import View.admin.panel.user_message.UserMessagePanel;
import Util.View.AdminPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 封装窗口与窗口信息获得方法，目的减少重复代码
 * */
public class FirstMenuPanel extends AdminPanel implements ActionListener, AdminJFrameConfig {
    private final JPanel buttonPanel;      //左栏按钮面板

    private final JButton usersManger;      //用户管理
    private final JButton orderManger;      //订单管理
    private final JButton powerBankManger;  //充电宝管理
    private final JButton exitSystem;       //退出系统
        public FirstMenuPanel() {
        super();
            //布局
            setLayout(new GridLayout(1,8));
            //按钮面板
            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(8,1));

            usersManger = new JButton("用户管理");
            usersManger.setHorizontalTextPosition(JButton.CENTER);
            orderManger = new JButton("订单管理");
            orderManger.setHorizontalTextPosition(JButton.CENTER);
            powerBankManger = new JButton("充电宝管理");
            powerBankManger.setHorizontalTextPosition(JButton.CENTER);
            exitSystem = new JButton("退出系统");
            exitSystem.setHorizontalTextPosition(JButton.CENTER);

            buttonPanel.add(usersManger);
            buttonPanel.add(orderManger);
            buttonPanel.add(powerBankManger);
            buttonPanel.add(exitSystem);

            add(buttonPanel,BorderLayout.CENTER);

            //注册事件
            usersManger.addActionListener(this);
            orderManger.addActionListener(this);
            powerBankManger.addActionListener(this);
            exitSystem.addActionListener(this);
        }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == usersManger && usersManger.getActionListeners().length > 0) {
            //主窗口渲染用户信息面板
            AdminJFrameManger.getAdminMainJFrameRenderingPanel().update(new UserMessagePanel());
        } else if (source == orderManger) {
            //主窗口渲染订单信息面板
            AdminJFrameManger.getAdminMainJFrameRenderingPanel().update(new OrderMessagePanel());
        } else if (source == powerBankManger) {
            //主窗口渲染充电宝信息面板
            AdminJFrameManger.getAdminMainJFrameRenderingPanel().update(new PowerBankMessagePanel());
        } else if (source == exitSystem) {
            //退出管理员系统
            SetServe.closeAdminMainJFrame();
        }
        //可添加鼠标悬停，显示更多菜单项
//        else if (source == usersManger && usersManger.getMouseMotionListeners().length > 0) {
//
//        }

    }

}
