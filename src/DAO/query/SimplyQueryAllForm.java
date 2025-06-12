package DAO.query;

import Util.db.DBQuary;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class SimplyQueryAllForm implements Query{


    /**
     * 按照写入的枚举返回表
     * */
    public ResultSet query(Object... firstEnumValue) {
        // 构建SQL语句，查询第一个枚举值对应的表
        String sql = "SELECT * FROM " + firstEnumValue;
        ResultSet rst = DBQuary.query(sql);

        return rst;
    }

}
