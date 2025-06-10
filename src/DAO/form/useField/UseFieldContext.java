package DAO.form.useField;

import java.util.List;

public class UseFieldContext {
    private UseField useField;

    /// 获得使用字段
    public List<String> getUseField() {
        return useField.getUseField();
    }

    /// 修改字段表
    public void setUseField(UseField useField) {
        this.useField = useField;
    }
}
