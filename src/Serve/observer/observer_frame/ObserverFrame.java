package Serve.observer.observer_frame;

import Serve.observer.Observer;
import View.FatherFrame;

import java.util.HashMap;

public class ObserverFrame extends Observer {
    public static HashMap<String, FatherFrame> observerFrame = new HashMap<>();

    public ObserverFrame(){}

    //添加窗口
    public static void addFrame(String name, FatherFrame frame){
        observerFrame.put(name, frame);
    }
    //获得窗口
    public static FatherFrame getFrame(String name){
        return observerFrame.get(name);
    }
    //获得所有窗口
    public static HashMap<String, FatherFrame> getAllFrame(){
        return observerFrame;
    }
    //移除窗口
    public static void removeFrame(String name){
        observerFrame.remove(name);
    }
}
