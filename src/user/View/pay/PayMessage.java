package user.View.pay;

import global.view_tool.MyJPanel;

import javax.swing.*;
import java.awt.*;

public class PayMessage extends MyJPanel {
    private final JLabel orders;                        //订单号
    private final JLabel name;                          //用户昵称
    private final JLabel address;                       //租借地址
    private final JLabel time;                          //租借时间
    private final JLabel powerID;                       //充电宝id编号
    private final JLabel deposit;                       //押金额度
    PayMessage() {
        setLayout(new GridLayout(8, 1));

        //订单号
        JPanel orderP = new JPanel(new GridLayout(1, 2));
        JLabel ordersL = new JLabel("订单号:");
        ordersL.setHorizontalAlignment(SwingConstants.RIGHT);
        orders = new JLabel();
        orderP.add(ordersL);
        orderP.add(orders);
        add(orderP);

        //用户昵称
        JPanel nameP = new JPanel(new GridLayout(1, 2));
        JLabel nameL = new JLabel("昵称:");
        nameL.setHorizontalAlignment(SwingConstants.RIGHT);
        name = new JLabel();
        nameP.add(nameL);
        nameP.add(name);
        add(nameP);

        //租借地址
        JPanel addressP = new JPanel(new GridLayout(1, 2));
        JLabel addressL = new JLabel("租借地址:");
        addressL.setHorizontalAlignment(SwingConstants.RIGHT);
        address = new JLabel();
        addressP.add(addressL);
        addressP.add(address);
        add(addressP);

        //租借时间
        JPanel timeP = new JPanel(new GridLayout(1, 2));
        JLabel timeL = new JLabel("租借时间:");
        timeL.setHorizontalAlignment(SwingConstants.RIGHT);
        time = new JLabel();
        timeP.add(timeL);
        timeP.add(time);
        add(timeP);

        //充电宝id编号
        JPanel powerIDP = new JPanel(new GridLayout(1, 2));
        JLabel powerIDL = new JLabel("充电宝编号:");
        powerIDL.setHorizontalAlignment(SwingConstants.RIGHT);
        powerID = new JLabel();
        powerIDP.add(powerIDL);
        powerIDP.add(powerID);
        add(powerIDP);

        //押金额度
        JPanel depositP = new JPanel(new GridLayout(1, 2));
        JLabel depositL = new JLabel("押金额度:");
        depositL.setHorizontalAlignment(SwingConstants.RIGHT);
        deposit = new JLabel();
        depositP.add(depositL);
        depositP.add(deposit);
        add(depositP);

        setVisible(true);
    }
}
