package View.factoryPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
/**
 * 使用组件工厂说明
 * 1.创建面板
 * 创建该组件工厂对象，调用createPanel()函数即可生产组件
 * 注意：请使用工厂的枚举确定要生产的组件类型，枚举带有多少个英文单词就至少填写多少的信息
 * 2.文本或属性、id信息设置
 * ; 前面为您要设置的文本，后面是您要设置的ID信息，id相当于组件特有的名字
 * */
public class FactoryPanel{
    private static final Logger logger = LogManager.getLogger(FactoryPanel.class);
    private Map<String, JComponent> panel = new HashMap();      //储存用户信息

    public FactoryPanel() {}

    /**
     * 获得创建面板里的组件
     * @param id 组件id信息
     * */
    public JComponent getJComponent( String id) {
        if (this.panel.get(id) != null) {
            return this.panel.get(id);
        } else {
            logger.error("该组件的父类面板没有被创建，获取失败");
            return null;
        }
    }

    public enum MyJPanelType {
        JLABLE_JTEXTFIELD_JLABLE,
        JLABLE_JPASSWORDFIELD_JBUTTON,
        JLABLE_JTEXTFIELD_JBUTTON,
        BUTTONS,
        JLABLE


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
                    JLabel noButtonInputLeft = new JLabel();
                    JTextField noButtonInputCENTER = new JTextField();
                    JLabel kong = new JLabel();
                    JPanel namePanel = new JPanel(new GridLayout(1, 3));
                    noButtonInputLeft.setHorizontalAlignment(JLabel.CENTER);
                    //记录组件id信息
                    setPanel(params[0],noButtonInputLeft);
                    setPanel(params[1],noButtonInputCENTER);
                    setPanel(params[2],namePanel);
                    namePanel.add(noButtonInputLeft);
                    namePanel.add(noButtonInputCENTER);
                    namePanel.add(kong);
                    //卸载id信息，防止文本或属性污染
                    params[0] = deleteID(params[0]);
                    params[1] = deleteID(params[1]);
                    params[2] = deleteID(params[2]);

                    //设置组件显示的文本信息或属性
                    noButtonInputLeft.setText(params[0]);
                    noButtonInputCENTER.setColumns(Integer.parseInt(params[1]));
                    kong.setText(params[2]);

                    return namePanel;

                    //密码输入框（请配合）
                case JLABLE_JPASSWORDFIELD_JBUTTON:
                    //参数：标签名称 + 密码输入框长度
                    JLabel passwordInputLeft = new JLabel();
                    JPasswordField passwordInputCenter = new JPasswordField();
                    JButton passwordRight = new JButton();

                    passwordInputLeft.setHorizontalAlignment(JLabel.CENTER);

                    //文字居中
                    JPanel passwordPanel = new JPanel(new GridLayout(1, 3));

                    //设置组件名称，返回组件
                    setPanel( params[0],passwordInputLeft);
                    setPanel( params[1],passwordInputCenter);
                    setPanel( params[2],passwordRight);
                    //布局
                    passwordPanel.add(passwordInputLeft);
                    passwordPanel.add(passwordInputCenter);
                    passwordPanel.add(passwordRight);

                    //卸载id信息，防止文本或属性污染
                    params[0] = deleteID(params[0]);
                    params[1] = deleteID(params[1]);
                    params[2] = deleteID(params[2]);

                    //设置组件显示的文本信息或属性
                    passwordInputLeft.setText(params[0]);
                    passwordInputCenter.setColumns(Integer.parseInt(params[1]));
                    passwordRight.setText(params[2]);
                    return passwordPanel;

                case JLABLE_JTEXTFIELD_JBUTTON:
                    //标签+普通输入框+发送验证码按钮
                    JPanel phonePanel = new JPanel(new GridLayout(1, 3));
                    JLabel buttonInputLeft = new JLabel();
                    buttonInputLeft.setHorizontalAlignment(JLabel.CENTER);
                    JTextField buttonInputCenter = new JTextField();
                    JButton buttonInputRight = new JButton();
                    //设置组件名称，返回组件内容
                    setPanel( params[0],buttonInputLeft);
                    setPanel( params[1],buttonInputCenter);
                    setPanel( params[2],buttonInputRight);
                    phonePanel.add(buttonInputLeft);
                    phonePanel.add(buttonInputCenter);
                    phonePanel.add(buttonInputRight);

                    //卸载id信息，防止文本或属性污染
                    params[0] = deleteID(params[0]);
                    params[1] = deleteID(params[1]);
                    params[2] = deleteID(params[2]);

                    //设置组件显示的文本信息或属性
                    buttonInputLeft.setText(params[0]);
                    buttonInputCenter.setColumns(Integer.parseInt(params[1]));
                    buttonInputRight.setText(params[2]);

                    return phonePanel;

                case BUTTONS:
                    int paramsNum = params.length; //传入参数数量
                    JPanel buttonPanel = new JPanel(new GridLayout(1,paramsNum));
                    JButton[] buttons = new JButton[paramsNum];
                    for (int i = 0; i < params.length; i++){
                        buttons[i] = new JButton();
                        //添加组件到面板中
                        buttonPanel.add(buttons[i]);
                        //设置组件id信息
                        String str = params[i];
                        setPanel( str, buttons[i]);
                        //卸载id信息，防止文本或属性污染
                        str = deleteID(params[i]);
                        //设置组件名称
                        buttons[i].setText(str);
                    }
                    return buttonPanel;

                case JLABLE:
                    int labelCount = params.length;
                    JPanel labelPanel = new JPanel(new GridLayout(1, labelCount));
                    JLabel[] labels = new JLabel[labelCount];

                    for (int i = 0; i < labelCount; i++) {
                        labels[i] = new JLabel();
                        //添加组件到面板中
                        labelPanel.add(labels[i]);
                        //设置组件id信息
                        labels[i].setHorizontalAlignment(JLabel.CENTER);
                        labels[i].setForeground(Color.RED);
                        //设置组件id信息
                        String str = params[i];
                        setPanel( str, labels[i]);
                        //卸载id信息，防止文本或属性污染
                        str = deleteID(params[i]);
                        //设置组件名称
                        labels[i].setText(str);
                    }

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
    /**
     * 保存已经创建的面板
     * @param panel MyJPanelType
     * @param id 组件id信息
     * */
    private void setPanel(  String id ,JComponent panel) {
        String nameid = id;
        //若有 ; 则记录此组件引用
        if (nameid.contains(";")) {
            //获得id信息
            int index = nameid.lastIndexOf(";");
            nameid = nameid.substring(index + 1);
            this.panel.put(nameid, panel);
        }
    }

    /**
     * 工厂卸载id信息函数
     * */
    private String deleteID(String id) {
        String nameid = id;
        //当字符串没有 ; 说明用户没有设置id意图，返回原本字符串
        if (!nameid.contains(";")) {
            return nameid;
        }
        //当字符串有 ; 删除附着在上面的id信息
        int index = nameid.lastIndexOf(";");
        nameid = nameid.substring(0, index);
        return nameid;
    }

}
