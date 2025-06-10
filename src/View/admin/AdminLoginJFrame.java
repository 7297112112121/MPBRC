package View.admin;

import View.admin.panel.auth.AdminLoginPanel;
import Util.View.DoorJFrame;
import Util.View.MyRenderingPanel;
import Util.CSSTool;

import javax.swing.*;
import java.awt.*;

public class AdminLoginJFrame extends DoorJFrame {
    private final MyRenderingPanel rp;        //渲染面板
    private final int width = 650;          //窗口宽度
    private final int height = 500;         //窗口高度

    public AdminLoginJFrame(int router) {
        super(router);
        //该窗口布局
        setLayout(new BorderLayout());
        //窗口居中
        setBounds(CSSTool.setCenter_X(width), CSSTool.setCenter_Y(height), width, height);
        setTitle("管理员，欢迎您！");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //渲染面板
        AdminLoginPanel lo = new AdminLoginPanel();
        rp = new MyRenderingPanel();
        rp.update(lo);

        //布局
        add(rp, BorderLayout.CENTER);

        setVisible(true);

        //关闭窗口尺寸改变
        setResizable(false);

    }
    //get
    @Override
    public MyRenderingPanel getRendering() {
        return rp;
    }
}
