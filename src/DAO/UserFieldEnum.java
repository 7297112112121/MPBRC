package DAO;

public enum UserFieldEnum {
    FORM("user"),
    NAMEID("nameid"),
    NAME("name"),
    SEX("sex"),
    PASSWORD("password"),
    PHONE("phone"),
    ADMINID("adminid"),
    CREATE_TIME("create_time"),
    ACCOUNT("account");

    private final String value;

    UserFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}