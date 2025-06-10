package DAO.register;

public class ContextRegister {
    private Register register;

    /// 设置注册方式
    public void setRegister(Register register){
        this.register = register;
    }

    /// 设置登陆方式
    public void register(Object obj){
        register.register(obj);
    }
}
