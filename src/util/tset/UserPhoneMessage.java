package util.tset;

import util.view_tool.MyJFrame;
import util.view_tool.RenderingPanel;

import javax.swing.*;

/**
 * 模拟用户的手机短信
 * */
public class UserPhoneMessage extends MyJFrame {
    public static UserPhoneMessage userPhoneMessage  = new UserPhoneMessage();

    private final JTextArea message;
    public UserPhoneMessage() {
        setTitle("模拟器——用户手机短信");
        setBounds(0,0,600,600);

        message = new JTextArea("手机短信消息：" + "\n");

        add(message);

        setVisible(true);
    }

    /**
     * 验证码消息
     * */
    public void getYangZhengMa(String mes) {
        this.message.append("【验证码】您的验证码是："+ mes + "\n"
                +"，请勿泄露给他人，如非本人操作，请忽略本短信。" + "\n");
    }

    //添加消息
    public void addMessage(String message) {
        this.message.append(message + "\n");
    }

    @Override
    public RenderingPanel getRendering() {
        return null;
    }

    public static UserPhoneMessage getUserPhoneMessage() {
        return userPhoneMessage;
    }
}
