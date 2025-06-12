package Util;

/**
 * 倒计时工具类，实现传入设置时间并传出倒计时秒数的功能
 */
public class CountDown implements Runnable {
    // 倒计时总时间（秒）
    private int totalSeconds;
    // 倒计时监听器，用于通知倒计时秒数变化
    private CountDownListener listener;
    // 用于控制线程是否继续运行
    private volatile boolean isRunning = true;

    /**
     * 构造函数，设置倒计时总时间
     * @param totalSeconds 倒计时总秒数
     */
    public CountDown(int totalSeconds) {
        this.totalSeconds = totalSeconds;
    }

    /**
     * 设置倒计时监听器
     * @param listener 倒计时监听器
     */
    public void setCountDownListener(CountDownListener listener) {
        this.listener = listener;
    }

    /**
     * 停止倒计时
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * 倒计时监听器接口，用于接收倒计时秒数变化通知
     */
    public interface CountDownListener {
        /**
         * 倒计时秒数更新时调用
         * @param seconds 剩余秒数
         */
        void onSecondUpdate(int seconds);

        /**
         * 倒计时结束时调用
         */
        void onCountDownFinish();
    }

    @Override
    public void run() {
        try {
            // 从总时间开始倒计时
            for (int i = totalSeconds; i >= 0 && isRunning; i--) {
                // 通知当前剩余秒数
                if (listener != null && i >= 0) {
                    listener.onSecondUpdate(i);
                }

                // 每秒更新一次
                if (i > 0) {
                    Thread.sleep(1000);
                }
            }

            // 倒计时结束通知
            if (listener != null && isRunning) {
                listener.onCountDownFinish();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}