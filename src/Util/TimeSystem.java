package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeSystem {
    private static final Logger logger = LogManager.getLogger(TimeSystem.class);
    // 定义日期时间格式化器，与数据库中的时间格式匹配
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前系统时间
     * @return 当前系统时间的 LocalDateTime 对象
     */
    public static LocalDateTime getCurrentSystemTime() {
        return LocalDateTime.now();
    }

    /**
     * 计算系统时间与数据库时间的差值，返回分钟数
     * @param dbTimeStr 数据库中的时间字符串，格式需为 yyyy-MM-dd HH:mm:ss
     * @return 时间差（分钟），如果解析数据库时间失败则返回 -1
     */
    public static long getMinutes(String dbTimeStr) {
        try {
            LocalDateTime dbTime = LocalDateTime.parse(dbTimeStr, DATE_TIME_FORMATTER);
            LocalDateTime systemTime = getCurrentSystemTime();
            return ChronoUnit.MINUTES.between(dbTime, systemTime);
        } catch (Exception e) {
            logger.error(e);
            return -1;
        }
    }

    /**
     * 计算系统时间与数据库时间的差值，返回小时数
     * @param dbTimeStr 数据库中的时间字符串，格式需为 yyyy-MM-dd HH:mm:ss
     * @return 时间差（小时），如果解析数据库时间失败则返回 -1
     */
    public static long getHours(String dbTimeStr) {
        try {
            LocalDateTime dbTime = LocalDateTime.parse(dbTimeStr, DATE_TIME_FORMATTER);
            LocalDateTime systemTime = getCurrentSystemTime();
            return ChronoUnit.HOURS.between(dbTime, systemTime);
        } catch (Exception e) {
            logger.error(e);
            return -1;
        }
    }

    /**
     * 计算系统时间与数据库时间的差值，返回天数
     * @param dbTimeStr 数据库中的时间字符串，格式需为 yyyy-MM-dd HH:mm:ss
     * @return 时间差（天），如果解析数据库时间失败则返回 -1
     */
    public static long getDays(String dbTimeStr) {
        try {
            LocalDateTime dbTime = LocalDateTime.parse(dbTimeStr, DATE_TIME_FORMATTER);
            LocalDateTime systemTime = getCurrentSystemTime();
            return ChronoUnit.DAYS.between(dbTime, systemTime);
        } catch (Exception e) {
            logger.error(e);
            return -1;
        }
    }
}
