package DAO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 定时更新数据线程
 * */
public class LoadFormDataThead implements Runnable{
    private static final Logger logger = LogManager.getLogger(LoadFormDataThead.class);
    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(5*60*1000);
            } catch (InterruptedException e) {
                logger.warn("定时更新数据线程异常",e);
            }
        }
    }
}
