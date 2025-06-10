package View.user;

import View.CSSJFrameConfig;
import Util.View.MyRenderingPanel;
import Util.View.UserJFrame;
import Util.View.UserRenderingPanel;
import View.user.index.IndexMain;

import javax.swing.*;
import java.awt.*;

public class UserMainJFrame extends UserJFrame implements CSSJFrameConfig {
    private final MyRenderingPanel renderingPanel;        //整页渲染面板
    private final IndexMain main;                        //整页面板

    public UserMainJFrame() {
        super();
        setTitle("租凭充电宝");
        setBounds(X_USER, Y_USER, WIDTH_USER, HEIGHT_USER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1,1));

        //关闭窗口尺寸改变
        setResizable(false);
        //整页面板
        main = new IndexMain();

        //渲染面板
        renderingPanel = new UserRenderingPanel(getUserID(), getDeviceID());
        add(renderingPanel);
        renderingPanel.update(main);

        setVisible(true);
    }

    public MyRenderingPanel getRendering() {
        return renderingPanel;
    }

}