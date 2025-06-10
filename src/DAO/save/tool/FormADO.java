package DAO.save.tool;

import DAO.save.DeviceFieldEnum;
import Util.ReflectionTool;
import Util.db.DBQuary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static Util.ReflectionTool.getEnumValues;

public class FormADO {
    private static final Logger logger = LogManager.getLogger(FormADO.class);

    private static List<String> AllField;

    /**
     * 按照写入的枚举返回表
     * @param list 要保存的表
     * @param enumClass 枚举类（）
     * 实现功能：传入以上参数，获得枚举中已定义字段的所有数据，并且以二维表格形式返还
     * */
    public static List<List<String>> loadDeviceForm(List<List<String>> list , Class<? extends Enum<?>> enumClass) {

        ////获得结果集
        List<String> field;
        String sql = "select * from device";
        ResultSet rst = DBQuary.query(sql);

        //获取需要查询的字段

        List<String> useField = new ArrayList<>();

        // 使用反射工具方法获取枚举值
        List<Object> values = getEnumValues(DeviceFieldEnum.class);

        // 将结果转换为字符串列表
        for (Object value : values) {
            if (value != null) {
                useField.add(value.toString());
            }
        }

        //清除原本表格所有内容
        list.clear();
        //获得该表所有字段
        field = DBQuary.getField(rst);
        setAllField(field);
        //获取使用的字段的所有信息
        DBQuary.getTable(rst, useField);
        //检查未使用字段
        DBQuary.checkUseFieldNumber(field, useField);
        return list;
    }


    private static void setAllField(List<String> field) {
        AllField = field;
    }

    public static List<String> getAllField() {
        return AllField;
    }


}
