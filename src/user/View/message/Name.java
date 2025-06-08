package user.View.message;

import data.UserForm;
import user.Service.LoginerMessageServe;
import user.User;
import global.view_tool.MyJPanel;
import global.view_tool.JFrameLayoutCenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.Service.UserRouter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Name extends MyJPanel implements ActionListener {
    private final static Logger logger = LogManager.getLogger(Name.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final static int width = 400 ;      //窗口宽度
    private final static int height = 400 ;     //窗口高度
    private final JTextField name;              //输入框
    private final JLabel remind;                //提示信息
    private final JButton submit;               //提交表单
    private final JButton cancel;               //取消提交表单
    public Name() {
        //设置面板基本设置
        setLayout(new GridLayout());
        setBounds(JFrameLayoutCenter.setCenter_X(width), JFrameLayoutCenter.setCenter_Y(height), width, height);
        //创建组件
        JPanel main = new JPanel(new GridLayout(6,1));      //主面板
        JLabel nameL = new JLabel("新昵称：");
        name = new JTextField(20);
        remind = new JLabel();
        remind.setForeground(Color.RED);        //字体为红色
        submit = new JButton("确认");
        cancel = new JButton("取消");

        //原昵称展示框
        JPanel onameP = new JPanel(new GridLayout(1,2));
        JLabel onameL = new JLabel("旧昵称：");
        JLabel oname = new JLabel(UserForm.getUser(User.getLogNameID()).getName());
        onameP.add(onameL);
        onameP.add(oname);
        main.add(onameP);

        //新昵称输入框
        JPanel nameP = new JPanel(new GridLayout(1,2));
        nameP.add(nameL);
        nameP.add(name);
        main.add(nameP);

        //提示信息
        JPanel remindP = new JPanel(new GridLayout(1, 2));
        remindP.add(remind);                        //提示信息组件
        main.add(remindP);

        //按钮 面板
        JPanel buttonP = new JPanel(new GridLayout(1,2));
        buttonP.add(submit);
        buttonP.add(cancel);
        main.add(buttonP);

        //添加到窗口
        add(main, BorderLayout.CENTER);
        //显示所有组件
        setVisible(true);

        //注册事件
        submit.addActionListener(this);
        cancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submit) {
            //提交表单
            String na = name.getText();
            if (!na.isEmpty()) {                    //非空
                if (!(na.equals(User.getUser().getName()))){   //名字不重复
                    if(LoginerMessageServe.upUserName(na)) {                        //更新成功
                        UserRouter.getTabPanel().update(new MessageMain());
                    }else {
                        remind.setText("程序遇到一点小问题，请重新确认，谢谢亲！");//数据库返回错误
                    }
                }else {
                    remind.setText("与旧昵称重复了哟！");
                }
            }else {
                remind.setText("请填写新昵称");                   //输入框为空
            }
        }
        if (source == cancel) {
            //返回用户中心
            UserRouter.getTabPanel().update(new MessageMain());
            logger.info(USER,"返回用户中心");
        }
    }

}
