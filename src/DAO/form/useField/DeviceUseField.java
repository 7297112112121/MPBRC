package DAO.form.useField;
import java.util.ArrayList;
import java.util.List;

public class DeviceUseField implements UseField {
    // 枚举
    public enum UseField {
        ID("id"),
        DEVICEID("deviceid");

        private String value;
        // 添加构造方法
        private UseField(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    /**
     * 获取需要查询的字段
     **/
    @Override
    public List<String> getUseField() {
        List<String> useField = new ArrayList<>();
        // 遍历枚举取值
        for (UseField field : UseField.values()) {
            useField.add(field.getValue());
        }
        return useField;
    }


}