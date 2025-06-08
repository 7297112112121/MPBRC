package admin.View;

import admin.View.panel.FirstMenuPanel;
import global.view_tool.JFrameLayoutCenter;
import global.view_tool.MyJFrame;
import global.view_tool.RenderingPanel;

import javax.swing.*;
import java.awt.*;



public class AdminMainJFrame extends MyJFrame implements AdminJFrameConfig{
    private final RenderingPanel rp;                         //右栏渲染面板
    private final int leftWidth = (int)(ADMIN_WIDTH * 0.3);  //左栏宽度占比30%,保持与主窗口大小一直
    private final int rightWidth = ADMIN_WIDTH - leftWidth;  //右栏占比剩余宽度
    private final FirstMenuPanel firstMenu;                  //菜单面板
    private final int width = ADMIN_WIDTH;                  //窗口宽度
    private final int height = ADMIN_HEIGHT;                //窗口高度

    public AdminMainJFrame() {
        setTitle("管理员主界面");
        //窗口居中
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);
        setLayout(new BorderLayout());

        //顶部信息
        JLabel text = new JLabel("充电宝租凭系统");
        JPanel jp = new JPanel();
        jp.add(text);
        add(jp, BorderLayout.NORTH);

        //左栏菜单面板
        firstMenu = new FirstMenuPanel();
        firstMenu.setSize(leftWidth, height);
        add(firstMenu, BorderLayout.WEST);

        //右栏渲染面板
        rp = new RenderingPanel();
        rp.setSize(rightWidth, height);
        add(rp, BorderLayout.CENTER);
        //显示窗口
        setVisible(true);
    }

    @Override
    public RenderingPanel getRendering() {
        return rp;
    }
}
