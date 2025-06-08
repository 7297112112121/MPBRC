package user.View.index;

import global.view_tool.MyJPanel;
import user.Service.UserRouter;
import user.View.pay.PayMain;

import javax.swing.*;
import java.awt.*;

/**
 * 扫码租借
 * */
public class BorrowButton extends MyJPanel {
    private final JButton borrow;

    public BorrowButton(){
        borrow = new JButton("扫码租借");
        add(borrow);

        //布局
        setLayout(new GridLayout(1,1));
        add(borrow);

        setVisible(true);

        //注册事件
        borrow.addActionListener(e -> {
            //打开支付页面
            UserRouter.getRouter().getUserMainJFrame().getRendering().update(new PayMain());
        });
    }

}
