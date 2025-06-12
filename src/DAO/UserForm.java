package DAO;

import DAO.query.SimplyQueryAllForm;

import java.sql.ResultSet;

public class UserForm {
    private UserForm() {}
    public ResultSet load() {
        return new SimplyQueryAllForm().query(UserFieldEnum.FORM);
    }

}
