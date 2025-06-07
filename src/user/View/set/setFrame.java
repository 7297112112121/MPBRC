package user.View.set;

import util.view_tool.MyJFrame;
import util.view_tool.RenderingPanel;
import util.view_tool.JFrameLayoutCenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.awt.*;

public class setFrame extends MyJFrame {
    private static final Logger logger = LogManager.getLogger(setFrame.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private OpenAdminJPanel openAdminJPanel;                    //创建管理员登陆面板
    private final static int width = 400;                       //窗口宽度
    private final static int height = 400;                      //窗口高度
    public setFrame() {
        setLayout(new GridLayout(1,1));

        openAdminJPanel = new OpenAdminJPanel();
        add(openAdminJPanel);
        //窗口设置
        setTitle("设置");
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        logger.info(USER,"打开设置窗口");
    }

    @Override
    public RenderingPanel getRendering() {
        return null;
    }
}
