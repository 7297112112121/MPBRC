package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
/**
 * 反射工具（非必要不使用）
 * */
public class ReflectionTool {
    private static final Logger logger = LogManager.getLogger(ReflectionTool.class);
    /**
     * 通过反射调用枚举类的 getValue() 方法，获取所有枚举值
     * @param enumClass 枚举类的Class对象
     * @return 包含所有枚举值的列表，如果调用失败则返回空列表
     */
    public static List<Object> getEnumValues(Class<? extends Enum<?>> enumClass) {
        List<Object> values = new ArrayList<>();

        if (enumClass == null) {
            logger.error("枚举Class对象为空");
            return values;
        }

        try {
            // 获取枚举的所有实例
            Enum<?>[] enumConstants = enumClass.getEnumConstants();

            // 获取getValue方法
            Method getValueMethod = enumClass.getMethod("getValue");

            // 遍历所有枚举实例并调用getValue方法
            for (Enum<?> constant : enumConstants) {
                Object value = getValueMethod.invoke(constant);
                values.add(value);
            }
        } catch (NoSuchMethodException e) {
            logger.error("枚举类中未找到 getValue() 方法", e);
        } catch (Exception e) {
            logger.error("调用 getValue() 方法失败", e);
        }

        return values;
    }
    /**
     * 获取枚举类的简单类名（不含包名）
     * @param enumClass 枚举类的Class对象
     * @return 枚举类的简单名，如 "Status"
     */
    public static String getEnumSimpleClassName(Class<? extends Enum<?>> enumClass) {
        if (enumClass == null) {
            logger.error("枚举Class对象为空");
            return null;
        }
        return enumClass.getSimpleName();
    }

    /**
     * 获取对象的简单类名（不含包名）
     * @param object 待获取类名的对象
     * @return 对象的简单类名，例如对于 java.util.Date 返回 "Date"
     */
    public static String getClassName(Object object) {
        if (object == null) {
            logger.error("对象为空");
            return null;
        }
        return object.getClass().getSimpleName();
    }

}