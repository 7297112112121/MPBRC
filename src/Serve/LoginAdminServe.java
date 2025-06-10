package Serve;

import Object.Admin;
import Util.erro.NameException;
import Util.erro.QueryPasswordException;

public class LoginAdminServe {

    public static void isName(String name) {
        if (name.isEmpty()){
            throw new NameException("管理员名称不能为空");
        }
    }

    public static void isPassword(String password) {
        if (password.isEmpty())
            throw new QueryPasswordException("管理员密码不能为空");

    }

    public static boolean login(String name, String password) {
        int i = AdminForm.getAdminID(name, password);
        //如果id小于0就是没有该管理员，报错
        if ( i < 0) {
            throw new NameException("管理员名称或密码错误");
        }
        //有的话返回该管理员的id
        Admin.setLoginID(i);
        return true;
    }
}
