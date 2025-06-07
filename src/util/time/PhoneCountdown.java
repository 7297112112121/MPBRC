package util.time;

import user.View.message.Phone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneCountdown implements Runnable {
    private final static Logger logger = LogManager.getLogger(Phone.class);
    private int time;
    private JButton button;
    private Timer timer;

    public PhoneCountdown() {
    }

    /**
     * 倒计时功能请调用该方法即可
     * */
    public void createCountdown(int time, JButton button) {
        this.time = time;
        this.button = button;
        // 启动新线程而不是直接调用run()
        new Thread(this).start();
    }

    @Override
    public void run() {
        // 使用Swing定时器来更新UI，确保线程安全
        timer = new Timer(1000, new ActionListener() {
            int remainingTime = time;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingTime >= 0) {
                    // 在EDT上更新UI
                    SwingUtilities.invokeLater(() -> {
                        button.setText(remainingTime + "s后重新发送");
                        button.setEnabled(false);
                    });
                    remainingTime--;
                } else {
                    // 倒计时结束
                    timer.stop();
                    SwingUtilities.invokeLater(() -> {
                        button.setText("发送验证码");
                        button.setEnabled(true);
                    });
                }
            }
        });
        timer.start();
    }
}