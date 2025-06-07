package user.View.jframe;

import user.View.entrance.Login;
import util.view_tool.MyJFrame;
import util.view_tool.RenderingPanel;
import util.view_tool.JFrameLayoutCenter;

import java.awt.*;


public class LoginJFrame extends MyJFrame {
    private final RenderingPanel rp;        //渲染面板
    private final Login lo;            //登录面板
    private final int width = 500;          //窗口宽度
    private final int height = 500;         //窗口高度
    public LoginJFrame() {
        super();
        //设置窗口布局
        setLayout(new BorderLayout());

        //关闭窗口尺寸改变
        setResizable(false);

        //设置渲染
        rp = new RenderingPanel();
        lo = new Login();
        rp.update(lo);

        //布局
        add(rp, BorderLayout.CENTER);

        //窗口的其他参数
        setTitle("用户登陆");
        setBounds(JFrameLayoutCenter.setCenter_X(width),
                JFrameLayoutCenter.setCenter_Y(height),
                width,
                height);
        setVisible(true);

    }

    @Override
    public RenderingPanel getRendering() {
        return rp;
    }

}




