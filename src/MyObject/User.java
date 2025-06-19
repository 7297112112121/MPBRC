package MyObject;

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
    private int nameID;
    private String name;
    private String sex;
    private String password;
    private String phone;
    private int adminID;
    private double account = 0.00;             //充值金额


    public User() {
        this.nameID = -1;
        this.adminID = -1;
        this.account = 0;
    }

    public User(String name) {
        this.name = name;
    }

    //登陆使用
    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.nameID=-1;
        this.adminID =-1;
        this.account = 0;


    }

    //注册使用
    public User(String name, String password, String phoneNum) {
        this(name, password);
        this.phone = phoneNum;
        this.nameID = -1;
        this.adminID = -1;
        this.account = 0;

    }

    public User(int nameID, String name, String password, String phoneNum) {
        this.nameID = nameID;
        this.name = name;
        this.password = password;
        this.phone = phoneNum;
        this.adminID = -1;
        this.account = 0;
    }

    //user表
    public User(int nameID, String name, String sex, String password, String phoneNum, String device_id) {
        this.name = name;
        this.password = password;
        this.phone = phoneNum;
        this.nameID = nameID;
    }

    //完整用户实例
    public User(int nameID, String name, String sex, String password, String phoneNum, double account) {
        this.nameID = nameID;
        this.name = name;
        this.sex = sex;
        this.password = password;
        this.phone = phoneNum;
        this.account = account;
    }


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





    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }
}
