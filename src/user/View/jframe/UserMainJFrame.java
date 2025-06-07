package user.View.jframe;

import util.view_tool.CreateLayout;
import util.view_tool.MyJFrame;
import util.view_tool.RenderingPanel;
import user.View.index.IndexMain;

import javax.swing.*;
import java.awt.*;

public class UserMainJFrame extends MyJFrame implements CreateLayout {
    private final RenderingPanel renderingPanel;        //整页渲染面板
    private final IndexMain main;                   //整页面板

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
        renderingPanel = new RenderingPanel();
        add(renderingPanel);
        renderingPanel.update(main);

        setVisible(true);
    }

    public RenderingPanel getRendering() {
        return renderingPanel;
    }

}