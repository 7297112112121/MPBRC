package admin.View.panel.user_message;

import admin.Serve.ModelProcessServe;
import admin.Serve.UserMessageManager;
import global.view_tool.MyJPanel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import user.Service.RegisterServe;
import user.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserMessage_AllPanel extends MyJPanel implements ActionListener {
    private static Logger logger = LogManager.getLogger(UserMessage_AllPanel.class);
    private static final Marker ADMIN = MarkerManager.getMarker("ADMIN");

    private final JTable allUserMessageTable ;              //所有用户信息表
    private final DefaultTableModel defaultt;               //所有用户信息表模型
    private  String[] columnNames= {"ID", "昵称", "性别", "电话", "管理员", "充值余额"};//所有用户信息表字段
    private final  JButton updataButton;                    //手动更新用户数据（重新从数据库读取）
    private final  JButton saveButton ;                     //保存修改的数据
    public  UserMessage_AllPanel(){
        setLayout(new BorderLayout());

        //所有用户信息表
        JPanel userMessagePanel = new JPanel();
        defaultt = new DefaultTableModel(columnNames ,0);
        allUserMessageTable  = new JTable(defaultt);
        JScrollPane scrollPane = new JScrollPane(allUserMessageTable);
        userMessagePanel.add(scrollPane);
        add(userMessagePanel, BorderLayout.CENTER);

        //按钮
        JPanel buttonPanel = new JPanel();
        updataButton = new JButton("更新");
        saveButton = new JButton("保存");
        buttonPanel.add(updataButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //注册
        updataButton.addActionListener(this);
        saveButton.addActionListener(this);

        //录入数据
        addDataUser();

        //全局显示

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == updataButton) {
            //手动更新并查询用户数据（从数据重新读取数据）
            defaultt.setRowCount(0);
            addDataUser();
            logger.info(ADMIN, "手动更新用户数据成功");
        } else if (source == saveButton) {
            //保存修改的数据
            //获得模型对象列表
            List<User> users = ModelProcessServe.createUsersList(defaultt);
            //更新数据库
            UserMessageManager.upAllUserMessage(users);
            logger.info(ADMIN, "保存用户数据成功");
        }
    }

    /**
     * 模型添加数据
     * */
    private void addDataUser() {
        ModelProcessServe.addRow(columnNames, defaultt);
    }

    /**
     * 获得表格模型所有数据
     * */
    private void getModelData() {
        ModelProcessServe.getModelData(defaultt);
    }
}
