package DAO.insert;

import Util.db.DBQuary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SimplyInsertWhere implements Insert{
    @Override
    public ResultSet insert(Enum... parms) {
        // 构建SQL语句，查询第一个枚举值对应的表
        String sql = "INSERT * FROM " + parms[0] + " WHERE " + parms[1] + " = " + parms[2];
        ResultSet rst = DBQuary.query(sql);
        //获取需要查询的字段
        List<String> useField = new ArrayList<>();
        //获得该表所有字段
        List<String> field = DBQuary.getField(rst);
        //获取使用的字段的所有信息
        DBQuary.getTable(rst, useField);
        //检查未使用字段
        DBQuary.checkUseFieldNumber(field, useField);
        return rst;
    }
}
