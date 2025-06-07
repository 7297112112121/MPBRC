package user;

import data.UserForm;

public class User {
    /*
    * 用户ID
    * 用户名
    * 性别
    * 用户设备id
    * 用户密码
    * 用户手机号码
    * 用户余额
    * */
    public static int logNameID = -1;
    private int nameID;
    private String name;
    private String sex;
    private String device_id;
    private String password;
    private String phone;
    private double balance;             //充值金额


    //登陆使用
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    //注册使用
    public User(String name, String password, String phoneNum) {
        this(name, password);
        this.phone = phoneNum;
    }

    public User(int nameID, String name, String password, String phoneNum) {
        this.nameID = nameID;
        this.name = name;
        this.password = password;
        this.phone = phoneNum;
    }

    //user表
    public User(int nameID, String name, String sex, String password, String phoneNum, String device_id) {
        this.name = name;
        this.password = password;
        this.phone = phoneNum;
        this.nameID = nameID;
        this.device_id = device_id;
    }

    //本系统用户实例
    public User(int nameID, String name, String sex, String password, String phoneNum, String device_id, double balance) {
        this.name = name;
        this.password = password;
        this.phone = phoneNum;
        this.nameID = nameID;
        this.device_id = device_id;
        this.balance = balance;
    }


    /**
     * 快速获得对象
     * **/
    public static User getUser(){
        return UserForm.getUser(User.getLogNameID());
    }


    public User() {}
    /**
    * 获取与设置实例变量
     * nameID
     * name
     * password
     * phoneNum
    * */

    public int getNameID() {
        return nameID;
    }

    public void setNameID(int nameID) {
        this.nameID = nameID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phoneNum) {
        this.phone = phoneNum;
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static int getLogNameID() {
        return logNameID;
    }

    public static void setLogNameID(int logNameID) {
        User.logNameID = logNameID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
