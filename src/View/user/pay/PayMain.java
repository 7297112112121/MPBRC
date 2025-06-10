package View.user.pay;

import Util.View.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class PayMain extends MyJPanel {
    private final JButton hone;                    //返回主页
    private final PayMessage payMessage;           //支付信息
    private final SelectJpanel selectPay;          //支付选择
    public PayMain() {
        //面板布局
        setLayout(new GridLayout(3, 1));

        //空面板
        JPanel emptyP = new JPanel();

        //返回主页
        hone = new JButton("返回主页");
        JPanel honeP = new JPanel(new GridLayout(1,4));
        honeP.add(hone);
        honeP.add(emptyP);
        honeP.add(emptyP);
        honeP.add(emptyP);
        add(honeP);

        //支付信息
        payMessage = new PayMessage();
        Panel payMessageP = new Panel(new GridLayout(1,1));
        payMessageP.add(payMessage);
        add(payMessageP);

        //支付选择
        selectPay = new SelectJpanel();
        Panel selectPayP = new Panel(new GridLayout(1,1));
        selectPayP.add(selectPay);
        add(selectPayP);

        setVisible(true);
    }
}
