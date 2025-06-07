package admin.View;

import util.view_tool.MyJFrame;
import util.view_tool.RenderingPanel;
import user.Service.pay.UserAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * 获得用户现在行为
 * */
public class Monitor extends MyJFrame implements ActionListener {
    private JTextArea monitorArea;  //监控区域
    private JButton updata;         //更新
    private JButton export;          //导出
    private int size = 0;
    public Monitor(){
        //窗口设置
        setTitle("用户行为监控");
        setSize(800,600);
        setLayout(new BorderLayout());

        //创建组件
        JPanel areaPanel = new JPanel();
        monitorArea = new JTextArea();
        JPanel buttonPanel = new JPanel();
        updata = new JButton("更新");
        export = new JButton("导出");

        //监控区域
        areaPanel.add(monitorArea);
        add(areaPanel,BorderLayout.CENTER);

        //按钮区域
        buttonPanel.add(updata);
        buttonPanel.add(export);
        add(buttonPanel,BorderLayout.SOUTH);

        //窗口显示
        setVisible(true);

        //注册监听
        updata.addActionListener(this);
        export.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == updata){
            //更新
            buttonUpData();
        } else if (source == export){
            //导出
            export();
        }
    }

    /**
     * 窗口开启读取所有现有用户行为信息
     * */
    public void getAllNews(JTextArea monitorArea){
        List<String> news = UserAction.getMessage().getAll();
        size = news.size();
        for(String s : news){
            monitorArea.append(s + "\n");
        }
    }

    /**
     * 手动更新方案
     * */
    public void buttonUpData(){
        size++;
        monitorArea.append(UserAction.getMessage().get(size) + "\n");
    }

    /**
     * 导出当前用户行为日志
     */
    public void export() {

    }

    @Override
    public RenderingPanel getRendering() {
        return null;
    }
}