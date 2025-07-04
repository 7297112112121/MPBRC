package Serve.observer.observer_frame;

import Serve.observer.Observer;
import View.FatherFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class AllObserverOfFrame extends Serve.observer.Observer {
    private static final Logger logger = LogManager.getLogger(AllObserverOfFrame.class);
    // 存储每个窗口对应的观察者列表，使用Map确保每种类型唯一
    public static HashMap<FatherFrame, Map<Type, Serve.observer.Observer>> allObserver = new HashMap<>();

    public AllObserverOfFrame(){}

    public enum Type {
        OBSERVER_MESSAGE_PANEL
    }

    // 添加观察者到指定窗口，确保每种类型唯一
    public static void add(FatherFrame frame, Type type, Serve.observer.Observer observer) {
        Map<Type, Serve.observer.Observer> frameObservers = allObserver.computeIfAbsent(frame, k -> new HashMap<>());

        // 检查是否已存在相同类型的观察者
        if (frameObservers.containsKey(type)) {
            logger.error("该窗口已存在类型为 " + type + " 的观察者");
            return;
        }

        frameObservers.put(type, observer);
    }

    // 获取指定窗口的特定类型观察者
    public static Serve.observer.Observer getObserverByFrame(FatherFrame frame, Type type) {
        Map<Type, Serve.observer.Observer> frameObservers = allObserver.get(frame);
        if (frameObservers != null) {
            return frameObservers.get(type);
        }  else {
            logger.error("该窗口不存在类型为 " + type + " 的观察者");
            return null;
        }
    }

    // 获取指定窗口的所有观察者
    public static Map<Type, Serve.observer.Observer> getObserversByFrame(FatherFrame frame) {
        return allObserver.getOrDefault(frame, Collections.emptyMap());
    }

    // 获取指定类型的观察者列表
    public static List<Serve.observer.Observer> getObserversByType(Type type) {
        List<Serve.observer.Observer> result = new ArrayList<>();
        for (Map<Type, Serve.observer.Observer> frameObservers : allObserver.values()) {
            if (frameObservers.containsKey(type)) {
                result.add(frameObservers.get(type));
            }
        }
        return result;
    }

    // 获取所有窗口及其观察者映射
    public static HashMap<FatherFrame, Map<Type, Serve.observer.Observer>> getAll() {
        return new HashMap<>(allObserver);
    }

    // 移除指定窗口的所有观察者
    public static void remove(FatherFrame frame) {
        allObserver.remove(frame);
    }

    // 移除指定窗口的特定类型观察者
    public static void remove(FatherFrame frame, Type type) {
        Map<Type, Observer> frameObservers = allObserver.get(frame);
        if (frameObservers != null) {
            frameObservers.remove(type);
            if (frameObservers.isEmpty()) {
                allObserver.remove(frame);
            }
        }
    }

}