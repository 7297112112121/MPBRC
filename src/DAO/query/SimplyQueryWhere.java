package DAO.query;

import Util.db.DBQuary;

import java.sql.ResultSet;

public class SimplyQueryWhere implements Query {
    @Override
    /* *
     * 按照写入的枚举返回表
     * */
    public ResultSet query(String... parms) {
        if (parms.length < 3) { // 至少需要表名、一个字段和一个值
            throw new IllegalArgumentException("参数数量不足");
        }

        String form = parms[0];
        // 奇数 -> 字段
        StringBuilder sql = new StringBuilder("SELECT * FROM " + form + " WHERE ");

        // 构建SQL语句
        for (int i = 1; i < parms.length - 1; i += 2) {
            sql.append(parms[i]).append(" = ?");
            if (i < parms.length - 3) { // 不是最后一个条件
                sql.append(" AND ");
            }
        }

        // 计算值的数量
        int valueCount = (parms.length - 1) / 2;
        String[] values = new String[valueCount];

        // 偶数 -> 值
        int count = 0;
        for (int i = 2; i < parms.length; i += 2) {
            values[count++] = parms[i];
        }

        return DBQuary.query(sql.toString(), values);
    }
}