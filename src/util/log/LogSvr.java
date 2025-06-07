package util.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 写入文件
 */

public class LogSvr {
    private static final Logger logger = LogManager.getLogger(LogSvr.class);
    //读取日志时间
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void logMsg(File logFile, String mesInfo) throws IOException {
        try{
            if (logFile == null) {
                throw new IllegalStateException("文件不能为空!");
            }
            Writer fileWriter = new FileWriter(logFile, true);
            fileWriter.write(dateFormat.format(new Date()) + "\t" + mesInfo + "\n");
            fileWriter.flush();
        }catch (IOException e) {
            logger.error(e);
        }
    }

}
