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


    /**
     * 获得创建面板里的组件
     * @param type MyJPanelType
     * @param position  String
     * */
    public JComponent getPanel( MyJPanelType type,String position) {
        String nam = position + type.toString() ;
        if (this.panel.get(nam) != null) {
            return this.panel.get(nam);
        } else {
            logger.error("该组件的父类面板没有被创建，获取失败");
            return null;
        }
    }

    /**
     * 保存已经创建的面板
     * @param panel MyJPanelType
     * @param position String
     * @param type  JComponent
     * */
    private void setPanel( MyJPanelType type,String position ,JComponent panel) {
        String nam = position + type.toString() ;
        //传入用户输入信息
        this.panel.put(nam, panel);
    }

    public enum MyJPanelType {
        JLABLE_JTEXTFIELD_JLABLE, JLABLE_JPASSWORDFIELD_JBUTTON, JLABLE_JTEXTFIELD_JBUTTON,
        BUTTONS, JLABLE


//        ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN
    }

    //面板抽象工厂
    /**
     * @param type 面板类型
     * 扩展要求：每个车间只能接受一个用户数据
     * */
    public JPanel createPanel(MyJPanelType type, String... params){
        try {


            switch (type) {
                case JLABLE_JTEXTFIELD_JLABLE:
                    //传参：标签内容+普通输入框长度
                    JLabel noButtonInputLeft = new JLabel(params[0]);
                    JTextField noButtonInputCENTER = new JTextField(Integer.parseInt(params[1]));
                    JLabel kong = new JLabel(params[2]);
                    JPanel namePanel = new JPanel(new GridLayout(1, 3));
                    noButtonInputLeft.setHorizontalAlignment(JLabel.CENTER);
                    setPanel(MyJPanelType.JLABLE_JTEXTFIELD_JLABLE,params[0],noButtonInputLeft);
                    setPanel(MyJPanelType.JLABLE_JTEXTFIELD_JLABLE,params[1],noButtonInputCENTER);
                    setPanel(MyJPanelType.JLABLE_JTEXTFIELD_JLABLE,params[2],namePanel);
                    namePanel.add(noButtonInputLeft);
                    namePanel.add(noButtonInputCENTER);
                    namePanel.add(kong);

                    return namePanel;

                    //密码输入框（请配合）
                case JLABLE_JPASSWORDFIELD_JBUTTON:
                    //参数：标签名称 + 密码输入框长度
                    JLabel passwordInputLeft = new JLabel(params[0]);
                    JPasswordField passwordInputCenter = new JPasswordField(Integer.parseInt(params[1]));
                    JButton passwordRight = new JButton(params[2]);

                    passwordInputLeft.setHorizontalAlignment(JLabel.CENTER);

                    //文字居中
                    JPanel passwordPanel = new JPanel(new GridLayout(1, 3));

                    //设置组件名称，返回组件
                    setPanel(MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON, params[0],passwordInputLeft);
                    setPanel(MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON, params[1],passwordInputCenter);
                    setPanel(MyJPanelType.JLABLE_JPASSWORDFIELD_JBUTTON, params[2],passwordRight);
                    //布局
                    passwordPanel.add(passwordInputLeft);
                    passwordPanel.add(passwordInputCenter);
                    passwordPanel.add(passwordRight);
                    return passwordPanel;

                case JLABLE_JTEXTFIELD_JBUTTON:
                    //标签+普通输入框+发送验证码按钮
                    JPanel phonePanel = new JPanel(new GridLayout(1, 3));
                    JLabel buttonInputLeft = new JLabel(params[0]);
                    buttonInputLeft.setHorizontalAlignment(JLabel.CENTER);
                    JTextField buttonInputCenter = new JTextField(Integer.parseInt(params[1]));
                    JButton buttonInputRight = new JButton(params[2]);
                    //设置组件名称，返回组件内容
                    setPanel(MyJPanelType.JLABLE_JTEXTFIELD_JBUTTON,params[0],buttonInputLeft);
                    setPanel(MyJPanelType.JLABLE_JTEXTFIELD_JBUTTON,params[1],buttonInputCenter);
                    setPanel(MyJPanelType.JLABLE_JTEXTFIELD_JBUTTON, params[2],buttonInputRight);
                    phonePanel.add(buttonInputLeft);
                    phonePanel.add(buttonInputCenter);
                    phonePanel.add(buttonInputRight);
                    return phonePanel;

                case BUTTONS:
                    int paramsNum = params.length; //传入参数数量
                    JPanel buttonPanel = new JPanel(new GridLayout(1,params.length));
                    JButton[] buttons = new JButton[paramsNum];
                    for (int i = 0; i < params.length; i++){
                        buttons[i] = new JButton(params[i]);
                        buttonPanel.add(buttons[i]);
                        String str = params[i];
                        setPanel(MyJPanelType.BUTTONS, str, buttons[i]);
                    }
                    return buttonPanel;

                case JLABLE:
                    JLabel label = new JLabel(params[0]);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setForeground(Color.RED);
                    JPanel labelPanel = new JPanel();
                    labelPanel.add(label);
                    setPanel(MyJPanelType.JLABLE,params[0],label);
                    return labelPanel;

                default:
                    logger.warn("PanelFactory工厂没有该对象,请检查是否创建或设置引用");
                    return null;
            }
        }catch (RuntimeException e){
            logger.error("数组为空，请传入参数",e);
            return null;
        }
    }

}
