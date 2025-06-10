package Object;

//创建普通用户
public class RegularUsers extends User {

    public RegularUsers(String name, String password) {
        super(name, password);
    }

    public RegularUsers(String name, String password, String phone) {
        super(name, password, phone);
    }

    public RegularUsers(int nameID, String name, String password, String phone) {
        super(nameID, name, password, phone);
    }

    public RegularUsers() {
    }
}
