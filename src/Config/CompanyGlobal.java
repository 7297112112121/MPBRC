package Config;

public class CompanyGlobal {
    //依据company表
    private static int companyID = 1;

    public static int getCompanyID() {
        return companyID;
    }

    public static void setCompanyID(int companyID) {
        CompanyGlobal.companyID = companyID;
    }
}
