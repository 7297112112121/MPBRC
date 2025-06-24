package Serve;

import Config.AuthCaptchaGlobal;
import Config.AuthGlobal;
import MyObject.PowerBankCabinet;
import Serve.observer.ObserverCabinet;
import Serve.rentPackage.LoadAllPlanRent;
import Util.db.DataBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 定时更新数据线程，支持静态方法触发立即更新
 * 使用单例模式确保全局只有一个实例
 * */
public class LoadDataThead implements Runnable {
    private static final Logger logger = LogManager.getLogger(LoadDataThead.class);
    private static final LoadDataThead INSTANCE = new LoadDataThead();
    private static final Lock staticLock = new ReentrantLock();
    private static final Condition staticUpdateCondition = staticLock.newCondition();
    private static boolean staticUpdateRequested = false;

    private static Thread dataThread;

    private LoadDataThead() {}

    /**
     * 获取单例实例
     */
    public static LoadDataThead getInstance() {
        return INSTANCE;
    }

    /**
     * 启动数据更新线程
     */
    public static void startThread() {
        if (dataThread == null || !dataThread.isAlive()) {
            synchronized (LoadDataThead.class) {
                if (dataThread == null || !dataThread.isAlive()) {
                    dataThread = new Thread(INSTANCE, "DataUpdateThread");
                    dataThread.setDaemon(true);
                    dataThread.start();
                    logger.info("数据更新线程已启动");
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                performUpdate();
                staticLock.lock();
                try {
                    if (!staticUpdateRequested) {
                        staticUpdateCondition.awaitNanos(30 * 1000 * 1000000L); // 5分钟
                    }
                    staticUpdateRequested = false; // 重置更新请求标志
                } finally {
                    staticLock.unlock();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("定时更新数据线程被中断", e);
                break;
            } catch (Exception e) {
                logger.error("数据更新过程中发生异常", e);
                // 等待一段时间再重试
                try {
                    Thread.sleep(10000); // 10秒
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    /**
     * 执行数据更新操作
     */
    private void performUpdate() {
        // 加载所有存在的充电宝柜
        new CabinetServer().loadCabinets();
        logger.info("所有充电宝柜加载成功");

        // 加载套餐
        LoadAllPlanRent.loadAllPlanRent();

        // 连接数据库，检查是否畅通
        DataBase.DBcheck();
        logger.info("数据库连接畅通");

        // 加载验证配置信息
        new AuthGlobal();
        new AuthCaptchaGlobal();
        logger.info("验证配置信息加载成功");
    }

    /**
     * 静态方法：触发立即更新
     */
    public static void triggerUpdate() {
        staticLock.lock();
        try {
            staticUpdateRequested = true;
            staticUpdateCondition.signal(); // 唤醒等待的线程
            logger.info("接收到立即更新请求");

            // 如果线程未启动，则自动启动
            startThread();
        } finally {
            staticLock.unlock();
        }
    }
}