package admin;

public class Admin {
    /**
     * 在线管理员id
     *
    * 该条信息id
     * 管理员名称
     * 管理员密码
     * 管理员手机号码
     * 管理员工作id
    * */
    private static int loginID = -1;

    private int ID;
    private String name;
    private String password;
    private String phone;
    private String workID;

    public Admin() {
    }

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Admin(String name, String password, String phone, String workID) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.workID = workID;
    }

    public Admin(int ID, String name, String password, String phone, String workID) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.workID = workID;
    }




    //set/get
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public static int getLoginID() {
        return loginID;
    }
    public static void setLoginID(int loginID) {
        Admin.loginID = loginID;
    }
}
