package user.Service.pay;

import data.UserForm;
import user.User;

/**
 * 简单方法封装
 * */
public class GetUser {
    public static User getUser(){
        return UserForm.getUser(User.getLogNameID());
    }
}
