package View.user.index;

import Config.UserMainJFrameText;
import Util.View.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class Text extends MyJPanel implements UserMainJFrameText {
    private final JLabel nameLabel;

    public Text() {

        //用户名标签
        nameLabel = new JLabel();
        //消息文本
        JTextArea news = new JTextArea();
        news.setEditable(false);

        //判断用户是否登录，生成不同面板
        if(Text.isLogin()) {
            OnlineUser onl = new OnlineUser();
            nameLabel.setText(USER_TEXT_Logged + onl.getUserOnline().getName());
            news.setText(USER_TEXT_Right);
        }else {
            nameLabel.setText(USER_TEXT_Not_logged);
            news.setText(USER_TEXT_Right);
        }

        //布局
        setLayout(new GridLayout(1, 2));
        add(nameLabel);
        add(news);

        //注册事件
        nameLabel.addMouseListener(new Click());
    }

    class Click extends MouseAdapter {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            if(!isLogin()) {
                UserRouter.getRouter().newJFrame(UserJFrame);//通知路由渲染 3 登录页面
            }
        }
    }

    /**
     * 检查用户是否登陆
     * 默认返回false
     * */
    public static boolean isLogin() {
        return new OnlineUser().getUserOnline() != null;
    }

    //get用户名标签
    public JLabel getNameLabel() {
        return nameLabel;
    }
}
