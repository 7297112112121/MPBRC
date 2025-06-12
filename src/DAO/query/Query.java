package DAO.query;

import java.sql.ResultSet;

public interface Query {
    ResultSet query(Object... params);
}
