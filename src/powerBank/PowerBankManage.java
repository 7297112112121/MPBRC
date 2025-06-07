package powerBank;

public interface PowerBankManage {
    /*
    * 移动电源管理
    * */

    //更新移动电源状态（租凭中、电量充足可租凭、不可租凭）
    void updateStatus();

    //展示所有可用电源信息（剩余电量>50%）
    String showAllPowerSupply();
}
