package DAO.insert;

import Util.db.DBQuary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SimplyInsertAllForm implements Insert{
    /**
     * 按照写入的枚举返回表
     * */
    public ResultSet insert(Enum... firstEnumValue) {
        // 构建SQL语句，查询第一个枚举值对应的表
        String sql = "INSERT * FROM " + firstEnumValue;
        ResultSet rst = DBQuary.query(sql);


        return rst;
    }
}
