package Util.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
            logger.error(e);
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

    /**
     * 记录结果集所有字段
     * @param res 结果集
     * */
    public static List<String> getField(ResultSet res) {
        try {
            //记录数据库字段
            List<String> field = new ArrayList<>();
            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                field.add( metaData.getColumnName(i));
            }
            return field;
        } catch (NullPointerException e) {
            logger.error("没有该表",e);
            return null;
        } catch (SQLException e2) {
            logger.error(e2);
            return null;
        }
    }

    /**
     * 记录使用过的字段
     * @param useField 使用过的字段
     * @param res 结果集
     * **/
    public static String[] getAlreadyUseField(String[] useField, ResultSet res) {
        try {
            String[] text = new String[30];
            for (int i = 0; i < useField.length - 1; i++) {
                text[i] = res.getString(useField[i]);
            }
            return text;
        } catch (SQLException e) {
            logger.warn(e);
            return null;
        }
    }



    /**
     * 转换二维表
     * 内部分List《String》是一条记录
     * 外部分List《...》是装所有记录
     * 故每条记录的序号是从0开始，而不是按数据库中表唯一的id
     * */
    public static List<List<String>> getTable(ResultSet res, List<String> useField) {
        List<List<String>> lists = new ArrayList<>();
        try {
            while (res.next()) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < useField.size(); i++) {
                    list.add(res.getString(useField.get(i)));
                }
                lists.add(list);
            }
            return lists;
        } catch (SQLException e) {
            logger.warn(e);
            return null;
        }
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
