package View.user.message;

import Util.View.MyJPanel;
import Config.UserMainJFrameText;
import Util.View.MyRenderingPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import Object.User;
import View.user.index.IndexMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageMain extends MyJPanel implements ActionListener, UserMainJFrameText {
    private final static Logger logger = LogManager.getLogger(MessageMain.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final JButton home;                     //返回主页
    private final Label name;                       //名字
    private final JButton nameSet;                  //名字设置按钮
    private final Label phone;                      //手机号码
    private final JButton phoneSet;                 //手机号码设置按钮
    private final Label password;                   //密码
    private final JButton passwordSet;              //密码设置按钮
    private final JButton exit;                     //退出登陆
    public MessageMain() {
        //父布局格式
        setLayout(new GridLayout(6,1));
        //创建组件
        home = new JButton("返回主页");
        JLabel nameL = new JLabel("用户名:");
        name = new Label();
        nameSet = new JButton("修改昵称");
        JLabel phoneL = new JLabel("手机号:");
        phone = new Label();
        phoneSet = new JButton("手机号重新绑定");
        JLabel passwordL = new JLabel("密码:");
        password = new Label();
        passwordSet = new JButton("修改密码");
        JLabel tab = new JLabel("\t");      //空格组件
        exit = new JButton("退出登陆");

        //返回主页面板
        JPanel homeP = new JPanel(new GridLayout(1, 3));
        homeP.add(home);
        homeP.add(tab);
        homeP.add(tab);
        add(homeP);

        //名称面板
        JPanel nameP = new JPanel(new GridLayout(1, 3));
        nameP.add(nameL);
        nameP.add(name);
        nameP.add(nameSet);                //设置按钮
        add(nameP);
        //电话面板
        JPanel phoneP = new JPanel(new GridLayout(1,3));
        phoneP.add(phoneL);
        phoneP.add(phone);
        phoneP.add(phoneSet);              //设置按钮
        add(phoneP);
        //密码
        JPanel passwordP = new JPanel(new GridLayout(1,3));
        passwordP.add(passwordL);
        passwordP.add(password);
        passwordP.add(passwordSet);        //设置按钮
        add(passwordP);
        //退出登陆
        JPanel exitP = new JPanel(new GridLayout(1, 2));
        exitP.add(exit);
        add(exitP);

        //设置数据
        User user = RouterForm.getOnlineObject(getRouterID()).getUserOnline();
        name.setText(user.getName());
        phone.setText(user.getPhone());
        password.setText(passwordNumber());

        //显示所有组件
        setVisible(true);

        //添加监听事件
        home.addActionListener(this);
        nameSet.addActionListener(this);
        phoneSet.addActionListener(this);
        passwordSet.addActionListener(this);
        exit.addActionListener(this);
    }
    
    /**
     * 监听事件
     * */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        MyRenderingPanel homeRP = RouterForm.getUserMainJFrame_RenderingPanel(getRouterID());
        if (source == home) {
            //打开用户主界面
            homeRP.update(new IndexMain());
            logger.info(USER,"返回主页");
        }
        if (source == nameSet) {
            //打开名称修改面板
            homeRP.update(new Name());
            logger.info(USER,"修改名称");
        }
        if (source == phoneSet) {
            //打开手机号修改面板
            homeRP.update(new Password());
            logger.info(USER,"修改手机号");
        }
        if (source == passwordSet) {
            //打开密码修改面板
            homeRP.update(new Phone());
            logger.info(USER,"修改密码");
        }
        if (source == exit) {
            OnlineUser user = RouterForm.getOnlineObject(getRouterID());
            //退出登陆
            logger.info(USER, "{}:退出登陆+ '\n'",user.getUserOnline().getName());
            RouterForm.setVIsible_UserLoginJFrame(getRouterID(),true);
            RouterForm.setVIsible_UserMainJFrame(getRouterID(),false);
            user.setUserOnline(-1);
        }
    }

    /**
     * get name、phone输入框内容
     * */
    public String getName() {
        return name.getText().trim();
    }

    public String getPhone() {
        return phone.getText().trim();
    }

    //按照密码字符数量显示等量 * 符号
    private String passwordNumber() {
        String s = "";
        OnlineUser user = RouterForm.getOnlineObject(getRouterID());
        int nameid = user.getUserOnline().getNameID();
        //从新数据库中获得密码长度
        int num = UserForm.getUser(nameid).getPassword().length();
        for(int i = 0; i < num; i++) {
            s = s + "*";
        }
        return s;
    }
}
