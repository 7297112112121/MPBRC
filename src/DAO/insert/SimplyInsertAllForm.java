package DAO.insert;

import Util.db.DBUpData;
import java.util.ArrayList;
import java.util.List;

public class SimplyInsertAllForm implements Insert {
    /**
     * 第 1 个参数：formName - 要插入数据的表名（字符串）
     * 第 2 个及之后参数：EnumValue - 可变参数，格式为：
     * "字段1", "字段2", ..., "字段n", "值1", "值2", ..., "值n"
     *
     * 字段名：表中的列名（字符串），多个字段用逗号分隔（实际通过分号;标识字段与值的分界）
     * 值：要插入的字段值（字符串），需与字段顺序一一对应
     * */
    public int insert(String formName, String... EnumValue) {
        // 确定字段与值的分割点
        int splitIndex = 0;
        for (String value : EnumValue) {
            if (value.contains(";")) {
                // 找到分割符，记录位置并移除分割符
                EnumValue[splitIndex] = value.replace(";", "");
                break;
            }
            splitIndex++;
        }

        // 确保至少有字段和一个值
        if (splitIndex >= EnumValue.length - 1) {
            throw new IllegalArgumentException("需要同时提供字段和值");
        }

        // 提取字段名
        String[] attributes = new String[splitIndex];
        System.arraycopy(EnumValue, 0, attributes, 0, splitIndex);

        // 构建SQL语句
        StringBuilder sqlAttribut = new StringBuilder();
        StringBuilder sqlValue = new StringBuilder();

        // 生成字段列表
        for (int i = 0; i < attributes.length; i++) {
            sqlAttribut.append(attributes[i]);
            if (i < attributes.length - 1) {
                sqlAttribut.append(",");
            }
        }

        // 生成占位符列表，数量基于字段数
        for (int i = 0; i < attributes.length; i++) {
            sqlValue.append("?");
            if (i < attributes.length - 1) {
                sqlValue.append(",");
            }
        }

        String sql = "INSERT INTO " + formName + "(" + sqlAttribut + ")" + " VALUES(" + sqlValue + ")";

        // 提取值部分（跳过分隔符位置，处理空值）
        List<String> paramList = new ArrayList<>();
        for (int i = splitIndex; i < EnumValue.length; i++) {
            String val = EnumValue[i];
            if (val != null && !val.trim().isEmpty()) { // 过滤空值和空白字符串
                paramList.add(val);
            }
        }
        String[] params = paramList.toArray(new String[0]);

        // 执行带参数的SQL，这里要确保 params 长度和占位符数量一致，否则会报错
        int number = DBUpData.update(sql, params);

        return number;
    }
}