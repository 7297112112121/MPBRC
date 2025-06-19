package View.admin;

import MyObject.Admin;
import MyObject.PowerBankCabinet;
import MyObject.User;
import Serve.observer.observer_frame.ObserverFrame;
import Serve.observer.observer_frame.ObserverMessagePanel;
import View.FatherFrame;
import View.FatherJPanel;
import View.RenderingPanel;
import View.user.LoginPanel;
import View.user.MessagePane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminFrame extends FatherFrame {

    private final JButton news = new JButton("消息");
    private final JLabel title = new JLabel("移动电源管理系统");
    private final MessagePane messagePane;              //消息组件
    private final RenderingPanel rp ;                   //渲染组件
    private Admin admin;                                  //登录用户信息
    private JPanel address;
    private double price = 0;                          //用户选择租借冲电宝的套餐
    private List<PowerBankCabinet> powerBankCabinetsOnMap = new ArrayList<>(); //  在地图上显示的充电柜
    private PowerBankCabinet powerBankCabinetDefault;  //   用户选择的充电宝柜台
    private int width = 600;
    private int height = 800;

    public AdminFrame() {
        super();
        setBounds(800,200,width,height);
        setLayout(new BorderLayout());
        setTitle("管理员界面");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //添加该窗口到窗口观察者的列表中（ObserverFrame）
        ObserverFrame.addFrame("面相管理员窗口",this);

        //顶部（持久存在）
        JPanel top = new JPanel(new BorderLayout());

        //创建消息面板
        messagePane = new MessagePane(this, "管理员界面顶部消息");
        //设置MessagePanel观察者
        ObserverMessagePanel observerMessagePanel = new ObserverMessagePanel(this);

        observerMessagePanel.addMessagePanel("管理员界面顶部消息",messagePane);
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

           // rp.update(messagePane);
            news.setEnabled(false);

        });

        setVisible(true);
    }

    @Override
    public void update(FatherJPanel panel) {
        rp.update(panel);
    }

    @Override
    public FatherJPanel getShowPanel() {
        return null;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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


    public PowerBankCabinet getPowerBankCabinetDefault() {
        return powerBankCabinetDefault;
    }

    public void setPowerBankCabinetDefault(PowerBankCabinet powerBankCabinetDefault) {
        this.powerBankCabinetDefault = powerBankCabinetDefault;
    }

    public List<PowerBankCabinet> getPowerBankCabinetsOnMap() {
        return powerBankCabinetsOnMap;
    }

    public void setPowerBankCabinetsOnMap(List<PowerBankCabinet> powerBankCabinetsOnMap) {
        this.powerBankCabinetsOnMap = powerBankCabinetsOnMap;
    }

    public JPanel getAddress() {
        return address;
    }

    public void setAddress(JPanel address) {
        this.address = address;
    }
}
