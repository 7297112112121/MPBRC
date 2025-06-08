package global.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class DBQuary extends DataBase {
    private static Logger logger = LogManager.getLogger(DBQuary.class);

    /**
     * 普通查询（不建议使用，存在sql注入问题）
     * */
    public static ResultSet query(String sql) {
        try {
            statement = getStatement();
            resultSet=statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            logger.warn("数据库服务没有开启，或数据库配置错误，检查DBConfig,若以上都不行，则定位逻辑错误");
            return null;
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
