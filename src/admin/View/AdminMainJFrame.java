package admin.View;

import global.view_tool.JFrameLayoutCenter;
import global.view_tool.MyJFrame;
import global.view_tool.RenderingPanel;

import java.awt.*;

public class AdminMainJFrame extends MyJFrame {
    private final RenderingPanel rp;
    private final int width = 1600;          //窗口宽度
    private final int height = 1200;         //窗口高度

    public AdminMainJFrame() {

        setTitle("管理员主界面");
        //窗口居中
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);
        setLayout(new BorderLayout());

        //渲染面板
        rp = new RenderingPanel();
        add(rp, BorderLayout.CENTER);

        //显示窗口
        setVisible(true);
    }

    @Override
    public RenderingPanel getRendering() {
        return rp;
    }
}
