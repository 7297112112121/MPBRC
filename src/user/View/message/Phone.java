package user.View.message;


import user.Service.Get;
import user.Service.LoginerMessageServe;
import user.User;
import global.view_tool.MyJPanel;
import global.view_tool.JFrameLayoutCenter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.Service.UserRouter;
import user.Service.CreateYangZhengMa;
import global.time.PhoneCountdown;
import global.tset.UserPhoneMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static global.PasswordConfig.PHONE_NUM;

public class Phone extends MyJPanel implements ActionListener {
    private final static Logger logger = LogManager.getLogger(Phone.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final JLabel orPhone ;          //原始手机号
    private final JTextField newPhone;      //新手机号码
    private final JLabel remind;            //提示信息
    private final JButton send;             //发送验证码
    private final JTextField yanzheng;      //验证码输入框
    private String number;                  //生成的验证码号
    private final JButton submit;           //确认
    private final JButton cancel;           //取消
    private final static int height = 400;  //窗口高度
    private final static int width = 500;   //窗口宽度
    public Phone() {
        //窗口设置
        setLayout(new BorderLayout());
        setBounds(JFrameLayoutCenter.setCenter_X(width),
                  JFrameLayoutCenter.setCenter_Y(height),
                  width,
                  height);//对准主窗口居中

        //组件创建
        JLabel orPhoneL = new JLabel("原手机号码:");
        orPhone = new JLabel();
        orPhone.setText(User.getUser().getPhone());
        JLabel newPhoneL = new JLabel("新手机号码:");
        newPhone = new JTextField();
        remind = new JLabel();
        remind.setForeground(Color.RED);        //设置字体颜色为红色
        send = new JButton("发送验证码");
        JLabel yanzhengL = new JLabel("验证码:");
        yanzheng = new JTextField();
        submit = new JButton("确认");
        cancel = new JButton("取消");

        JPanel main = new JPanel(new GridLayout(6,1));
        JLabel kong = new JLabel("     "); //空标签
        //原手机号码
        JPanel orPhoneP = new JPanel(new GridLayout(1,3));
        orPhoneP.add(orPhoneL);
        orPhoneP.add(orPhone);
        orPhoneP.add(kong);
        main.add(orPhoneP);

        //新手机号码
        JPanel newPhoneP = new JPanel(new GridLayout(1,3));
        newPhoneP.add(newPhoneL);
        newPhoneP.add(newPhone);
        newPhoneP.add(send);
        main.add(newPhoneP);

        //提示信息
        JPanel remindP = new JPanel(new GridLayout(1,1));
        remindP.add(kong);
        remindP.add(remind);
        main.add(remindP);

        //验证码
        JPanel yanzhengP = new JPanel(new GridLayout(1,3));
        yanzhengP.add(yanzhengL);
        yanzhengP.add(yanzheng);
        yanzhengP.add(kong);
        main.add(yanzhengP);

        //确认取消
        JPanel buttonP = new JPanel(new GridLayout(1,2));
        buttonP.add(submit);
        buttonP.add(cancel);
        main.add(buttonP);

        add(main, BorderLayout.CENTER);

        //显示所有组件
        setVisible(true);

        //注册事件
        submit.addActionListener(this);
        cancel.addActionListener(this);
        send.addActionListener(this);
    }

    //事件处理


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submit && !isNull() && isPhone() && !isOldPhone() && isYanzheng()) {
            //输入不为空,符合手机号码格式,不是原手机号码
            LoginerMessageServe.upUserPhone(getNewPhone());
            Get.getMainUserFrame_Rendering().update(new MessageMain());
            JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
        if (source == cancel) {
            //返回用户中心
            UserRouter.getRouter().getUserMainJFrame().getRendering().update(new MessageMain());
            logger.info(USER,"返回用户中心");
        }
        if (source == send && !isNull() && isPhone() && !isOldPhone()) {
            //发送验证码
            remind.setText("验证码已发送");
            number = CreateYangZhengMa.verificationNum(6);
            UserPhoneMessage.getUserPhoneMessage().getYangZhengMa(number);
            logger.info(USER,"发送验证码成功: {}", number);
            //启动计时启，60s后重新设置文字为：发送验证码
            new PhoneCountdown().createCountdown(60,send);
        }
    }

    /**
     * 计时器（60s）
     */

    private boolean isNull() {
        boolean fals = getNewPhone().isEmpty();
        if (fals) {
            remind.setText("手机号码不能为空!");
        }
        return fals;
    }

    private boolean isPhone() {
        boolean fals = getNewPhone().matches(PHONE_NUM);
        if (!fals){
            remind.setText("手机号码格式不正确!");
        }
        return fals;
    }

    private boolean isOldPhone() {
        boolean fals = getNewPhone().equals(orPhone.getText().trim());
        if (fals) {
            remind.setText("与原手机号码相同!");
        }
        return fals;
    }

    private boolean isYanzheng() {
        boolean fals = yanzheng.getText().trim().equals(number);
        if (!fals) {
            remind.setText("验证码错误!请重新输入");
        }
        return fals;
    }

    private String getNewPhone() {
        return newPhone.getText().trim();
    }
}
