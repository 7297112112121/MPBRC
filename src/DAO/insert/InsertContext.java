package DAO.insert;

import java.sql.ResultSet;

public class InsertContext {
    private Insert insert;

    public void setInsert(Insert insert) {
        this.insert = insert;
    }

    public ResultSet insert(Enum... parms) {
        return insert.insert(parms);
    }
}
