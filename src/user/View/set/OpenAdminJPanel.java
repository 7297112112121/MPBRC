package user.View.set;

import global.view_tool.MyJPanel;
import admin.Serve.AdminRouterServe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAdminJPanel extends MyJPanel implements ActionListener {
    private final JButton openAdmin;        //打开管理员界面
    public OpenAdminJPanel() {
        super();

        openAdmin = new JButton("管理员模式");
        add(openAdmin);

        //注册监听器
        openAdmin.addActionListener(this);
    }

    /**
     * 进入管理员登录界面
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == openAdmin) {
            AdminRouterServe.getRouter().newJFrame(AdminJFrame);
        }
    }

}
