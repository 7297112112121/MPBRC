package Util.db.set;

import Util.db.DBUpData;

public class SimplySet {

    /**
     * 更新数据库表数据
     * @param from 要更新的表名
     * @param str 可变参数，格式要求：[字段1, 值1, 字段2, 值2, ..., ";", 条件字段1, 条件值1, 条件字段2, 条件值2, ...]
     *            分号前是 SET 部分的字段和值，分号后是 WHERE 部分的条件字段和值
     * @return 受影响的行数，更新失败返回 -1
     */
    public int set(String from, String... str) {
        // 去除表名首尾空格
        String tableName = from.trim();
        // 用于拼接 SET 部分的 SQL 片段
        StringBuilder setSql = new StringBuilder("UPDATE " + tableName + " SET ");
        // 用于拼接 WHERE 部分的 SQL 片段
        StringBuilder whereSql = new StringBuilder(" WHERE ");
        // 存储 SET 部分的值
        StringBuilder setValues = new StringBuilder();
        // 存储 WHERE 部分的值
        StringBuilder whereValues = new StringBuilder();

        // 标记是否已遇到分号，用于区分 SET 和 WHERE 部分
        boolean meetSemicolon = false;
        // 记录 SET 部分的字段 - 值对数量
        int setCount = 0;
        // 记录 WHERE 部分的条件 - 值对数量
        int whereCount = 0;

        for (int i = 0; i < str.length; i++) {
            String current = str[i].trim();
            if (";".equals(current)) {
                meetSemicolon = true;
                continue;
            }

            if (!meetSemicolon) {
                // 处理 SET 部分，格式：字段=? , 字段=?
                if (i < str.length - 1) {
                    setSql.append(current).append(" = ?");
                    setValues.append(str[i + 1]).append(",");
                    setCount++;
                    // 跳过下一个值，因为已经处理
                    i++;
                    if (i < str.length - 1 && !";".equals(str[i + 1].trim())) {
                        setSql.append(", ");
                    }
                }
            } else {
                // 处理 WHERE 部分，格式：字段=? AND 字段=?
                if (i < str.length - 1) {
                    whereSql.append(current).append(" = ?");
                    whereValues.append(str[i + 1]).append(",");
                    whereCount++;
                    // 跳过下一个值，因为已经处理
                    i++;
                    if (i < str.length - 1 && !";".equals(str[i + 1].trim())) {
                        whereSql.append(" AND ");
                    }
                }
            }
        }

        // 处理值字符串，去除末尾多余的逗号
        String setValueStr = setValues.length() > 0 ? setValues.substring(0, setValues.length() - 1) : "";
        String whereValueStr = whereValues.length() > 0 ? whereValues.substring(0, whereValues.length() - 1) : "";

        // 拼接完整 SQL
        StringBuilder fullSql = new StringBuilder();
        fullSql.append(setSql).append(whereSql);
        String sql = fullSql.toString();

        // 拼接所有参数值，SET 的值在前，WHERE 的值在后
        String[] allValues = new String[setCount + whereCount];
        if (setCount > 0) {
            String[] setArr = setValueStr.split(",");
            System.arraycopy(setArr, 0, allValues, 0, setCount);
        }
        if (whereCount > 0) {
            String[] whereArr = whereValueStr.split(",");
            System.arraycopy(whereArr, 0, allValues, setCount, whereCount);
        }
        // 确保 allValues 是 Object[] 类型（如 String[] 等）
        return DBUpData.update(sql, (Object[]) allValues);
    }
}