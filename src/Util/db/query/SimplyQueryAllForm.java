package Util.db.query;

import Util.db.DBQuary;

import java.sql.ResultSet;


public class SimplyQueryAllForm implements Query{


    /**
     * 按照写入的枚举返回表
     * */
    public ResultSet query(String... firstEnumValue) {
        // 构建SQL语句，查询第一个枚举值对应的表
        String sql = "SELECT * FROM " + firstEnumValue[0];
        System.out.println(sql);
        ResultSet rst = DBQuary.query(sql);

        return rst;
    }

}
