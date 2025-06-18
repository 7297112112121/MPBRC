package Util.db.query;

import java.sql.ResultSet;

public interface Query {
    ResultSet query(String... params);
}
