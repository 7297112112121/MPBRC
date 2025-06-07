package util.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DataBase implements DBConfig{
    private static final Logger logger = LogManager.getLogger(DataBase.class);
    protected static Connection conn;
    protected static Statement statement;
    protected static ResultSet resultSet;


    /**
     * 加载驱动与连接数据库
     * mysql84
     * */
    public static Connection getConnection() {
        try {
            Class.forName(driver);
            conn= DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("请检查是否开启数据库服务，若已开启请检查数据库配置是否正确，配置接口：@/util.db/DB/Config.java", e);
        }
        return null;
    }

    //Object... parameters   意思是多个Object类型数据
    /**
     * 创建PreparedStatement对象（推荐启用，有效防止sql注入）
     * */
    public static PreparedStatement getPreparedStatement(String sql, Object... parameters) throws SQLException {

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //获得元数据
        ParameterMetaData parameterMetaData = preparedStatement.getParameterMetaData();
        //获得字符串需要的参数个数
        int count = parameterMetaData.getParameterCount();
        //判断什么情况才需要给preparedStatement赋值
        //判断解释:当字符串需要的参数不为0，传入的parameters的数量不为0，且字符串需要的参数等于传入的parameter
        if(count != 0 && parameters != null && parameters.length == count){
            for (int i = 0; i < count; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
        }
        return preparedStatement;
    }

    /**
     * 创建Statement对象（不建议使用，推荐预处理）
     * */
    public static Statement getStatement() {
        try {
            statement=getConnection().createStatement();
            return statement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 数据库连接检测
     * @return true连接成功，false连接失败
     * */
    public static boolean DBcheck() {
        boolean fals = false;
        try {
            Connection conn = getConnection();
            if (conn.isValid(5)) {//5秒内连接成功
                fals = true;
            }
        } catch (SQLException e) {
            logger.error("数据库连接失败", e);
        } catch (NullPointerException e2) {
            logger.error("数据库连接对象为空", e2);
        }
        return fals;
    }

    /**
     * 关闭数据库连接
     * */
    public static void close() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
