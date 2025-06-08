package admin.View;

import global.view_tool.MyJFrame;
import global.view_tool.RenderingPanel;
import global.view_tool.JFrameLayoutCenter;

import javax.swing.*;
import java.awt.*;

public class AdminJFrame extends MyJFrame {
    private final RenderingPanel rp;        //渲染面板
    private final int width = 650;          //窗口宽度
    private final int height = 500;         //窗口高度

    public AdminJFrame(){
        super();
        //该窗口布局
        setLayout(new BorderLayout());
        //窗口居中
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);
        setTitle("管理员，欢迎您！");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //渲染面板
        AdminLoginPanel lo = new AdminLoginPanel();
        rp = new RenderingPanel();
        rp.update(lo);

        //布局
        add(rp, BorderLayout.CENTER);

        setVisible(true);

        //关闭窗口尺寸改变
        setResizable(false);

    }
    //get
    @Override
    public RenderingPanel getRendering() {
        return rp;
    }
}
