package global.log;

import user.Service.UserAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogView {
    private static final Logger logger = LogManager.getLogger(LogView.class);
    private BufferedReader reader;
    private File currentFile;
    private ScheduledExecutorService executor;
    private boolean isMonitoring = false;

    public synchronized void realtimeShowLog(File logFile) {
        // 检查是否已经在监控
        if (isMonitoring) {
            logger.warn("已经在监控日志文件: {}", logFile.getAbsolutePath());
            return;
        }

        try {
            // 初始化带缓存的读取器
            currentFile = logFile;
            reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(logFile),
                            StandardCharsets.UTF_8
                    )
            );
        } catch (FileNotFoundException e) {
            logger.error("文件不存在: {}", logFile.getAbsolutePath(), e);
            return;
        }

        // 启动监控任务
        executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(new LogMonitorTask(), 0, 5, TimeUnit.SECONDS);
        isMonitoring = true;
    }

    //停止监控任务
    public synchronized void stopMonitoring() {
        if (executor != null) {
            executor.shutdown();
            closeReader();
            isMonitoring = false;
        }
    }

    private void closeReader() {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                logger.error("关闭读取器失败", e);
            }
            reader = null;
        }
    }

    private class LogMonitorTask implements Runnable {
        @Override
        public void run() {
            synchronized (LogView.this) {
                if (!isMonitoring || reader == null) return;

                try {
                    // 检查文件是否被重置或删除
                    if (!currentFile.exists()) {
                        logger.warn("文件不存在，停止监控: {}", currentFile.getAbsolutePath());
                        stopMonitoring();
                        return;
                    }

                    // 读取新增行
                    String line;
                    while ((line = reader.readLine()) != null) {
                        UserAction.getMessage().add(line);// 已使用UTF-8编码
                    }
                } catch (IOException e) {
                    logger.error("读取文件时出错", e);
                    // 出错时重置读取器
                    closeReader();
                    try {
                        reader = new BufferedReader(
                                new InputStreamReader(
                                        new FileInputStream(currentFile),
                                        StandardCharsets.UTF_8
                                )
                        );
                    } catch (FileNotFoundException ex) {
                        logger.error("重新打开文件失败", ex);
                        stopMonitoring();
                    }
                }
            }
        }
    }
}