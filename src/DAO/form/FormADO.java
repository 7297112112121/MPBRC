package DAO.form;

import DAO.form.useField.UseField;
import DAO.form.useField.UseFieldContext;
import Util.db.DBQuary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.List;

public class FormADO {
    private static final Logger logger = LogManager.getLogger(FormADO.class);

    /**
     * @param list 要保存的表
     * @param useFields 表的类型
     * @param field 要保存的所有字段
     * */
    public static List<List<String>> loadDeviceForm(List<List<String>> list , List<String> field, UseField useFields) {
        //获得结果集
        String sql = "select * from device";
        ResultSet rst = DBQuary.query(sql);
        //获取使用字段
        UseFieldContext userFieldContext = new UseFieldContext();   //创建上下文
        userFieldContext.setUseField(useFields);                 //指定使用字段
        List<String> useField = userFieldContext.getUseField();     //获取使用字段
        //清除原本表格所有内容
        list.clear();
        //获得该表所有字段
        field = DBQuary.getField(rst);
        //获取结果信息
        DBQuary.getTable(rst, useField);
        //检查未使用字段
        DBQuary.checkUseFieldNumber(field, useField);
        return list;
    }
}
