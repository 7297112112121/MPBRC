package DAO.query;

import java.sql.ResultSet;

public class ContextQuery {
    private Query query;

    //配置查询模式
    public void setQuery(Query query) {
        this.query = query;
    }
    //使用方法
    public ResultSet query( String... params) {
        return query.query( params);
    }
}
