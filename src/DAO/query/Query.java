package DAO.query;

import java.sql.ResultSet;

public interface Query {
    ResultSet query(String... params);
}
