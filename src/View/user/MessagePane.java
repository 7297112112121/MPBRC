package View.user;

import View.MyFrame;
import View.MyJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePane extends MyJPanel  {
    private MyFrame frame;
    private TextArea message;
    public MessagePane(MyFrame frame) {
        super();
        setLayout(new BorderLayout());
        this.frame = frame;
        JButton button = new JButton("返回");
        button.addActionListener(e -> {
            this.frame.update(frame.getShowPanel());
        });
        add(button , BorderLayout.SOUTH);

        message = new TextArea();
        add(message , BorderLayout.CENTER);
    }

    public void addMessage(String msg) {
         message.append(msg);
    }
}
