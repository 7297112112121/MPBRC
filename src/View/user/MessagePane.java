package View.user;

import View.MyFrame;
import View.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePane extends MyJPanel  {
    private String messagePanelID;
    private MyFrame frame;
    private TextArea message;
    public MessagePane(MyFrame frame, String messagePanelID) {
        super();
        setLayout(new BorderLayout());
        this.frame = frame;
        JButton button = new JButton("返回");
        button.addActionListener(e -> {
            this.frame.update(frame.getShowPanel());
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
