package user.View.pay;

import util.view_tool.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.Service.UserRouter;
import user.Service.PaymentContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectJpanel extends MyJPanel implements ActionListener {
    private static final Logger logger = LogManager.getLogger(UserRouter.class);
    private static final Marker USER = MarkerManager.getMarker("USER");
    private final JButton weChat;       //微信支付按钮
    private final JButton aliPay;       //支付宝支付按钮
    private PaymentContext pay;         //支付策略上下文
    public SelectJpanel() {
        setLayout(new GridLayout(1, 2));

        //创建组件
        weChat = new JButton("微信支付");
        aliPay = new JButton("支付宝支付");

        //创建策略上下文
        pay = new PaymentContext();

        //微信支付
        add(weChat);

        //支付宝支付
        add(aliPay);

        //显示组件
        setVisible(true);

        //注册事件
        weChat.addActionListener(this);
        aliPay.addActionListener(this);


    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Object source = e.getSource();
//        try {
//            if (source == weChat) {
//                //微信支付策略
//                pay.setStrategy(new WeChatPay());
//                pay.executePayment(
//                        UserLogin.getUser().getNameID(),
//                        Account.getAccount().getId(),
//                        UserLogin.getUser().getWechat_balance()
//                );
//            }
//            if (source == aliPay) {
//                //支付宝支付策略
//                pay.setStrategy(new AlipayPay());
//                pay.executePayment(
//                        UserLogin.getUser().getNameID(),
//                        Account.getAccount().getId(),
//                        UserLogin.getUser().getAlipay_balance()
//                );
//            }
//        }catch (NullPointerException e2) {
//            logger.warn("空对象 - 用户登陆状态表为空（UserLogin）",e2);
//        }
//
//    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
