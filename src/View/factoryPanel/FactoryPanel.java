package View.factoryPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FactoryPanel{
    private static final Logger logger = LogManager.getLogger(FactoryPanel.class);
    private Map<String, JComponent> panel = new HashMap();      //储存用户信息

    public FactoryPanel() {}

    public void setPanel( MyJPanelType type,MyJPanelType position ,JComponent panel) {
        String nam = position.toString() + type.toString() ;
        //传入用户输入信息
        this.panel.put(nam, panel);
    }

    /**
     * 获得创建面板里的组件
     * */
    public JComponent getPanel( MyJPanelType type,MyJPanelType position) {
        String nam = position.toString() + type.toString() ;
        if (this.panel.get(nam) != null) {
            return this.panel.get(nam);
        } else {
            logger.error("该组件的父类面板没有被创建，获取失败");
            return null;
        }
    }

    public enum MyJPanelType {
        NO_Button_INPUT_JPANEL, PASSWORD_INPUT_JPANEL, Button_INPUT_JPANEL,

        LEFT,RIGHT, CENTER,REMIND
    }

    //面板抽象工厂
    /**
     * @param type 面板类型
     * 扩展要求：每个车间只能接受一个用户数据
     * */
    public JPanel createPanel(MyJPanelType type, String... params){
        try {


            switch (type) {
                case NO_Button_INPUT_JPANEL:
                    //传参：标签内容+普通输入框长度
                    JLabel noButtonInputLeft = new JLabel(params[0]);
                    JTextField noButtonInputCENTER = new JTextField(Integer.parseInt(params[1]));
                    JLabel nameRemind = new JLabel();

                    JPanel namePanel = new JPanel(new GridLayout(2, 2));

                    //文字居中
                    nameRemind.setHorizontalAlignment(JLabel.CENTER);
                    //设置红色字体
                    nameRemind.setForeground(Color.RED);
                    setPanel(MyJPanelType.NO_Button_INPUT_JPANEL,MyJPanelType.LEFT,noButtonInputLeft);
                    setPanel(MyJPanelType.NO_Button_INPUT_JPANEL,MyJPanelType.CENTER,noButtonInputCENTER);
                    setPanel(MyJPanelType.NO_Button_INPUT_JPANEL,MyJPanelType.REMIND,nameRemind);
                    namePanel.add(noButtonInputLeft);
                    namePanel.add(noButtonInputCENTER);
                    namePanel.add(nameRemind);
                    return namePanel;

                    //密码输入框（请配合）
                case PASSWORD_INPUT_JPANEL:
                    //参数：标签名称 + 密码输入框长度
                    JLabel passwordInputLeft = new JLabel(params[0]);
                    JPasswordField passwordInputCenter = new JPasswordField(Integer.parseInt(params[1]));
                    JButton passwordRight = new JButton("显示");
                    JLabel passwordLabel = new JLabel();

                    //文字居中
                    JPanel passwordPanel = new JPanel(new GridLayout(1, 3));
                    JPanel passwordPanel2 = new JPanel(new GridLayout(1, 3));

                    passwordLabel.setHorizontalAlignment(JLabel.CENTER);
                    //设置红色字体
                    passwordLabel.setForeground(Color.RED);
                    //设置组件名称，返回组件
                    setPanel(MyJPanelType.PASSWORD_INPUT_JPANEL,MyJPanelType.LEFT,passwordInputLeft);
                    setPanel(MyJPanelType.PASSWORD_INPUT_JPANEL,MyJPanelType.CENTER,passwordInputCenter);
                    setPanel(MyJPanelType.PASSWORD_INPUT_JPANEL, MyJPanelType.RIGHT,passwordRight);
                    setPanel(MyJPanelType.PASSWORD_INPUT_JPANEL, MyJPanelType.REMIND,passwordLabel);
                    //布局
                    passwordPanel.add(passwordInputLeft);
                    passwordPanel.add(passwordInputCenter);
                    passwordPanel.add(passwordRight);
                    passwordPanel2.add(passwordLabel);
                    JPanel passwordPanelAll = new JPanel(new GridLayout(2, 1));
                    passwordPanelAll.add(passwordPanel);
                    passwordPanelAll.add(passwordPanel2);
                    return passwordPanelAll;

                case Button_INPUT_JPANEL:
                    //标签+普通输入框+发送验证码按钮
                    JPanel phonePanel = new JPanel(new GridLayout(2, 2));
                    JLabel buttonInputLeft = new JLabel(params[0]);
                    JTextField buttonInputCenter = new JTextField(Integer.parseInt(params[1]));
                    JButton buttonInputRight = new JButton(params[2]);
                    //设置组件名称，返回组件内容
                    setPanel(MyJPanelType.Button_INPUT_JPANEL,MyJPanelType.LEFT,buttonInputLeft);
                    setPanel(MyJPanelType.Button_INPUT_JPANEL,MyJPanelType.CENTER,buttonInputCenter);
                    setPanel(MyJPanelType.Button_INPUT_JPANEL, MyJPanelType.RIGHT,buttonInputRight);
                    phonePanel.add(buttonInputLeft);
                    phonePanel.add(buttonInputCenter);
                    phonePanel.add(buttonInputRight);
                    return phonePanel;

                default:
                    logger.warn("PanelFactory工厂没有该对象");
                    return null;
            }
        }catch (RuntimeException e){
            logger.error("数组为空，请传入参数",e);
            return null;
        }
    }

}
