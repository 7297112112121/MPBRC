package View.user;

import MyObject.User;
import Serve.charge.chargeLocal;
import Util.factoryPanel.FactoryPanel;
import View.FatherJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class RechargePanel extends FatherJPanel implements ActionListener {
    private static final Logger logger = LogManager.getLogger(RechargePanel.class);
    private UserFrame frame;
    private chargeLocal rechargeLocal = new chargeLocal();
    private User user;
    FactoryPanel factoryPanel = new FactoryPanel();
    String textCharge = "支持0-300元充值";
    public RechargePanel(UserFrame frame) {
        logger.info("充值界面正在加载");
        this.frame = frame;
         user = frame.getUser();
        setLayout(new GridLayout(6,1));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JLABLE,"当前余额：", ";money", ""));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS,"100元;100", "50元;50", "30元;30"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "20元;20", "10元;10", "3元;3"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.JTEXTFIELD,"20;text"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "自定义充值;自定义"));
        add(factoryPanel.createPanel(FactoryPanel.MyJPanelType.BUTTONS, "返回;返回"));

        //设置默认文本信息
        //显示用户余额

        JLabel money = (JLabel) factoryPanel.getJComponent("money");
        money.setText(String.valueOf(user.getAccount()) + "￥");
        JTextField text = (JTextField) factoryPanel.getJComponent("text");

        //自定义输入文本提示事件
        // 设置初始提示文本
        text.setText(textCharge);
        // 添加焦点监听器
        text.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // 获得焦点时，如果是提示文本则清空
                String currentText = text.getText().trim();
                if (textCharge.equals(currentText)) {
                    text.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                // 失去焦点时，验证输入是否为0-300的数字
                String currentText = text.getText().trim();

                // 如果是空字符串，恢复提示文本
                if (currentText.isEmpty()) {
                    text.setText(textCharge);
                    return;
                }

                // 验证是否为0-300的整数
                try {
                    int value = Integer.parseInt(currentText);
                    if (value >= 0 && value <= 300) {
                        // 有效输入，保持不变
                    } else {
                        text.setText(textCharge);
                    }
                } catch (NumberFormatException ex) {
                    // 非数字输入，恢复提示文本
                    text.setText(textCharge);
                }
            }
        });

        //充值事件注册
        JButton m100 = (JButton) factoryPanel.getJComponent("100");
        JButton m50 = (JButton)  factoryPanel.getJComponent("50");
        JButton m30 = (JButton)  factoryPanel.getJComponent("30");
        JButton m20 = (JButton)  factoryPanel.getJComponent("20");
        JButton m10 = (JButton)  factoryPanel.getJComponent("10");
        JButton m3 = (JButton)   factoryPanel.getJComponent("3");
        JButton m = (JButton)    factoryPanel.getJComponent("自定义");
        m100.addActionListener(this);
        m50.addActionListener(this);
        m30.addActionListener(this);
        m20.addActionListener(this);
        m10.addActionListener(this);
        m3.addActionListener(this);
        m.addActionListener(this);

        //返回事件
        JButton back = (JButton) factoryPanel.getJComponent("返回");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.update(new CheckMoneyPanel(frame));
            }
        });
        logger.info("充值界面加载完成");
    }

   /**
    * 充值事件
    * 100
    * 50
    * 30
    * 20
    * 10
    * 3
    * 用户输入金额 0-300之间浮动
    * */

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton && ((JButton) source).getText().equals("100元")) {
            boolean fa = rechargeLocal.recharge(100.00, user.getNameID(), user);
            if (fa) {
                //刷新显示金额
                double money = user.getAccount();
                JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                moneyLabel.setText(String.valueOf(money));
            }
        } else if (source instanceof JButton && ((JButton) source).getText().equals("50元")) {
            boolean fa = rechargeLocal.recharge(50.00, user.getNameID(), user);
            if (fa) {
                //刷新显示金额
                double money = user.getAccount();
                JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                moneyLabel.setText(String.valueOf(money));
            }
        } else if (source instanceof JButton && ((JButton) source).getText().equals("30元")) {
            boolean fa =  rechargeLocal.recharge(30.00, user.getNameID(), user);
            if (fa) {
                //刷新显示金额
                double money = user.getAccount();
                JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                moneyLabel.setText(String.valueOf(money));
            }
        } else if (source instanceof JButton && ((JButton) source).getText().equals("20元")) {
            boolean fa = rechargeLocal.recharge(20.00, user.getNameID(), user);
            if (fa) {
                //刷新显示金额
                double money = user.getAccount();
                JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                moneyLabel.setText(String.valueOf(money));
            }
        } else if (source instanceof JButton && ((JButton) source).getText().equals("10元")) {
            boolean fa = rechargeLocal.recharge(10.00, user.getNameID(), user);
            if (fa) {
                //刷新显示金额
                double money = user.getAccount();
                JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                moneyLabel.setText(String.valueOf(money));
            }
        } else if (source instanceof JButton && ((JButton) source).getText().equals("3元")) {
            boolean fa = rechargeLocal.recharge(3.00, user.getNameID(), user);
            if (fa) {
                //刷新显示金额
                double money = user.getAccount();
                JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                moneyLabel.setText(String.valueOf(money));
            }
        } else if (source instanceof JButton && ((JButton) source).getText().equals("自定义充值")) {
            JTextField text = (JTextField) factoryPanel.getJComponent("text");
            String currentText = text.getText().trim();
            // 如果是提示文本或空输入，不进行充值
            if (textCharge.equals(currentText) || currentText.isEmpty()) {
                return;
            }
            // 严格验证输入是否为合法数字
            int amount = Integer.parseInt(currentText);
            // 检查范围
            if (amount >= 0 && amount <= 300) {
                // 调用充值方法（使用int避免浮点数精度问题）
                boolean fa = rechargeLocal.recharge(amount, user.getNameID(), user);
                if (fa) {
                    //刷新显示金额
                    double money = user.getAccount();
                    JLabel moneyLabel = (JLabel) factoryPanel.getJComponent("money");
                    moneyLabel.setText(String.valueOf(money));
                }
            }

        }
    }
}
