package View.user;

import Util.DoorMyRenderingPanel;
import Util.View.DoorJFrame;
import View.user.entrance.Login;
import Util.View.MyRenderingPanel;
import Util.CSSTool;

import java.awt.*;


public class UserLoginJFrame extends DoorJFrame {
    private final MyRenderingPanel rp;        //渲染面板
    private final Login lo;            //登录面板
    private final int width = 500;          //窗口宽度
    private final int height = 500;         //窗口高度
    public UserLoginJFrame(int loginID) {
        super(loginID);
        //设置窗口布局
        setLayout(new BorderLayout());

        //关闭窗口尺寸改变
        setResizable(false);

        //设置渲染
        rp = new DoorMyRenderingPanel(getDoorID());
        lo = new Login();
        rp.update(lo);

        //布局
        add(rp, BorderLayout.CENTER);

        //窗口的其他参数
        setTitle("用户登陆");
        setBounds(CSSTool.setCenter_X(width),
                CSSTool.setCenter_Y(height),
                width,
                height);
        setVisible(true);

    }

    public MyRenderingPanel getRendering() {
        return rp;
    }

}




