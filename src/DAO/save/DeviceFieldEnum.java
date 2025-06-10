package DAO.save;

// 独立枚举类
public enum DeviceFieldEnum {
    ID("id"),
    DEVICEID("deviceid");

    private final String value;

    DeviceFieldEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}