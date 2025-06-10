package Util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Reflection {
    private static final Logger logger = LogManager.getLogger(Reflection.class);
    private List<Class<?>> type;      // 数据类型
    private List<String> attribute;   // 字段
    private List<Object> value;       // 值

    public Reflection() {
        this.type = new ArrayList<>();
        this.attribute = new ArrayList<>();
        this.value = new ArrayList<>();
    }

    /**
     * 反射非静态变量
     * */
    public void traverseFields(Object object) {
        if (object == null) {
            logger.error("对象为空");
            return;
        }

        // 获取对象的 Class
        Class<?> clazz = object.getClass();

        // 获取所有声明的字段（包括 private、protected、public）
        Field[] fields = clazz.getDeclaredFields();

        // 遍历每个字段
        for (Field field : fields) {
            // 跳过静态字段
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            //跳过final字段
            if (Modifier.isFinal(field.getModifiers())){
                continue;
            }


            // 设置可访问性（允许访问 private 字段）
            field.setAccessible(true);

            // 获取字段名称和类型
            String fieldName = field.getName();
            attribute.add(fieldName);
            Class<?> fieldType = field.getType();
            type.add(fieldType);

            // 获取字段值
            try {
                Object fieldValue = field.get(object);
                value.add(fieldValue);
            } catch (IllegalAccessException e) {
                logger.error("无法访问字段 " + fieldName, e);
                value.add(null); // 添加 null 以保持列表同步
            }
        }
    }

    public List<Class<?>> getType() {
        return type;
    }

    public List<String> getAttribute() {
        return attribute;
    }

    public List<Object> getValue() {
        return value;
    }
}