package Object;

public class RegularAdmin extends Admin {
    public RegularAdmin() {

    }

    public RegularAdmin(int ID, String name, String password, String phone, String workID) {
        super(ID, name, password, phone, workID);
    }

    public RegularAdmin(String name, String password) {
        super(name, password);
    }
}
