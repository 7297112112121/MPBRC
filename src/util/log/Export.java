package util.log;

import user.Service.pay.UserAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Export {
    private static final Logger log = LogManager.getLogger(Export.class);

    public static void export(){
        List<String> list = UserAction.getMessage().getAll();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("user_action.log"));
            for (String line : list) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    log.error("文件关闭失败", e);
                }
            }
        }
    }
}
