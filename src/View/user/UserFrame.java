package View.user;

import View.MyFrame;
import View.MyJPanel;
import View.RenderingPanel;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends MyFrame {
    private final JButton news = new JButton("消息");
    private final JLabel title = new JLabel("欢迎使用租凭充电包系统");
    private final MessagePane messagePane;
    private final RenderingPanel rp ;
    public UserFrame() {
        super();
        setBounds(800,200,600,800);
        setLayout(new BorderLayout());
        setTitle("用户界面");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //顶部（持久存在）
        JPanel top = new JPanel(new BorderLayout());
        messagePane = new MessagePane(this);
        title.setHorizontalAlignment(JLabel.CENTER);
        top.add(title, BorderLayout.CENTER);
        top.add(news, BorderLayout.EAST);
        add(top,BorderLayout.NORTH);

        //主要内容
        rp = new RenderingPanel();
        add(rp,BorderLayout.CENTER);
        rp.update(new LoginPanel(this));


        //事件
        news.addActionListener(e -> {
            rp.update(messagePane);
        });
        setVisible(true);
    }
    public void update(MyJPanel panel) {
        rp.update(panel);
    }

    public MyJPanel getShowPanel() {
        return rp.getShowPanel();
    }

}
