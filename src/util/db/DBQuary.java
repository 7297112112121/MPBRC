package util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class DBQuary extends DataBase {


    /**
     * 普通查询（不建议使用，存在sql注入问题）
     * */
    public static ResultSet query(String sql) {
        try {
            statement = getStatement();
            resultSet=statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 预处理-执行查询操作
     * setString（）
     * @return 返回多行数据
     * */
    public static ResultSet query(String sql,Object... parameters) throws SQLException {
        //获得getPreparedStatement
        PreparedStatement preparedStatement = getPreparedStatement(sql, parameters);
        //返回结果集
        return preparedStatement.executeQuery();
    }



    @Deprecated
    //预处理-执行查询操作（废弃）
    public static ResultSet query(String sql, HashMap<Integer, String> params) {
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            Iterator<Integer> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                int num = iterator.next();
                ps.setString(num, params.get(num));
            }
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
