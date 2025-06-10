package Util.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DBUpData extends DataBase {

    /**
     * 更新数据库
     * 1、普通更新
     * 2、预处理更新
     * */

    /**
     * 普通更新
     * */
    public static int update(String sql) {
        try {
            statement = getStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 预处理-执行插入操作
     * setString（）
     * @return 返回多行数据
     * */
    public static int update(String sql,Object... parameters) throws SQLException {
        //获得getPreparedStatement
        PreparedStatement preparedStatement = getPreparedStatement(sql, parameters);
        //开始操作，并返回影响行数，
        int s = preparedStatement.executeUpdate();
        //返回影响数(处理了多少条数据)
        return s ;
    }


    /**
     * 执行插入并返回生成的主键
     * */
    public static int updateGetGeneratedKey(String sql, Object... params) {
        try {
            conn = DataBase.getConnection();
            // 关键：通过 PreparedStatement.RETURN_GENERATED_KEYS 参数请求返回生成的主键
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // 设置参数
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            pstmt.executeUpdate();

            // 获取生成的主键
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // 返回自增的 nameid
            }
            return -1; // 生成失败
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    @Deprecated
     //预处理-更新
    public static int update(String sql, HashMap<Integer, String> params) {
        try {
            if (getConnection() != null) {
                PreparedStatement ps = getConnection().prepareStatement(sql);
                for (Integer key : params.keySet()) {//每个字段进行setSting()处理
                    ps.setString(key, params.get(key));
                }
                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //错误返回-1
        return -1;
    }

}
