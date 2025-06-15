package View.user;

import MyObject.User;
import View.FatherFrame;
import View.FatherJPanel;
import View.RenderingPanel;
import Serve.observer.frame.ObserverFrame;
import Serve.observer.frame.ObserverMessagePanel;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends FatherFrame {
    private final JButton news = new JButton("消息");
    private final JLabel title = new JLabel("欢迎使用租凭充电包系统");
    private final MessagePane messagePane;
    private final RenderingPanel rp ;
    private User user;
    private double price = 0;//用户选择租借冲电宝的套餐
    private int width = 600;
    private int height = 800;
    public UserFrame() {
        super();
        setBounds(800,200,width,height);
        setLayout(new BorderLayout());
        setTitle("用户界面");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加该窗口到窗口观察者的列表中（ObserverFrame）
        ObserverFrame.addFrame("面相用户窗口",this);

        //顶部（持久存在）
        JPanel top = new JPanel(new BorderLayout());

        //创建消息面板
        messagePane = new MessagePane(this, "用户界面顶部消息");
        //设置MessagePanel观察者
        ObserverMessagePanel observerMessagePanel = new ObserverMessagePanel(this);

        observerMessagePanel.addMessagePanel("用户界面顶部消息",messagePane);
        //设置标题格式
        title.setHorizontalAlignment(JLabel.CENTER);
        top.add(title, BorderLayout.CENTER);
        top.add(news, BorderLayout.EAST);
        add(top,BorderLayout.NORTH);

        //主要内容
        rp = new RenderingPanel(this);
        add(rp,BorderLayout.CENTER);
        rp.update(new LoginPanel(this));

        //消息事件
        news.addActionListener(e -> {

                rp.update(messagePane);
                news.setEnabled(false);

        });
        setVisible(true);
    }
    public void update(FatherJPanel panel) {
        rp.update(panel);
    }

    //返回上一级
    public FatherJPanel getShowPanel() {
        return rp.getShowPanel();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JButton getNews() {
        return news;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
