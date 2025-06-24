package Serve;

public enum PowerBank {
    POWER_RENTAL_ING("租凭中"),
    POWER_RENTAL_NO("不可租凭"),
    POWER_RENTAL_YES("可租凭"),

    POWER_SHELF_YES("已上架"),
    POWER_SHELF_NO("已下架");
    private String status;
    PowerBank(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
}
