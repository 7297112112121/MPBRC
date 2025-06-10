package View.user.index;

import Object.User;
import Util.View.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import View.user.message.MessageMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nav extends MyJPanel implements ActionListener {
    private static final Logger logger = LogManager.getLogger(Nav.class);
    private static final Marker USER = MarkerManager.getMarker("USER");

    private final JButton my, orders, set;      //三个按钮

    public Nav() {

        my = new JButton("我的");
        orders = new JButton("订单");
        set = new JButton("设置");

        //布局
        setLayout(new GridLayout(1,3));
        add(my);
        add(orders);
        add(set);

        setVisible(true);

        //注册监听
        my.addActionListener(this);
        set.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object so = e.getSource();
        if (so == my) {
            if(isLogin()) {
                //用户已经登陆则打开用户面板,打开用户中心面板
                UserRouter.getRouter().getUserMainJFrame().getRendering().update(new MessageMain());
                //消息通知
                logger.info(USER, "{}:进入用户中心", UserForm.getUser(User.getLogNameID()).getName());
            }else {
                //用户没有登录则打开用户登陆窗口
                UserRouter.getRouter().newJFrame(UserJFrame);
            }
        }
        if (so == set) {
            UserRouter.getRouter().newJFrame(SetFrame);
        }
    }

    public static boolean isLogin() {
        return UserForm.getUser(User.getLogNameID()) != null;
    }

}
