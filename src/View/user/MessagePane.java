package View.user;

import View.FatherFrame;
import View.FatherJPanel;

import javax.swing.*;
import java.awt.*;

public class MessagePane extends FatherJPanel {
    private String messagePanelID;
    private UserFrame frame;
    private TextArea message;
    public MessagePane(FatherFrame frame, String messagePanelID) {
        super();
        setLayout(new BorderLayout());
        this.frame = (UserFrame) frame;

        JButton button = new JButton("返回");
        button.addActionListener(e -> {
            this.frame.update(frame.getShowPanel());
            //允许首页按钮显示
            ((UserFrame) frame).getNews().setEnabled(true);
        });
        add(button , BorderLayout.SOUTH);

        message = new TextArea();
        //关闭编辑
        message.setEditable(false);
        add(message , BorderLayout.CENTER);
    }

    public void addMessage(String msg) {
         message.append(msg);
    }

    public String getMessagePanelID() {
        return messagePanelID;
    }
}
