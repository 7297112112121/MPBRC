package admin.View.entrance;

import util.view_tool.MyJFrame;
import util.view_tool.RenderingPanel;
import util.view_tool.JFrameLayoutCenter;
import admin.View.entrance.panel.Admin_Login;

import javax.swing.*;
import java.awt.*;

public class AdminJFrame extends MyJFrame {
    private final RenderingPanel rp;        //渲染面板
    private final int width = 400;          //窗口宽度
    private final int height = 400;         //窗口高度

    public AdminJFrame(){
        super();
        //该窗口布局
        setLayout(new BorderLayout());

        //渲染面板
        Admin_Login lo = new Admin_Login();
        rp = new RenderingPanel();
        rp.update(lo);

        //布局
        add(rp, BorderLayout.CENTER);

        setTitle("管理员，欢迎您！");               //窗口的其他参数
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }
    //get
    @Override
    public RenderingPanel getRendering() {
        return rp;
    }
}
