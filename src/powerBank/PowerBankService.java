package powerBank;

import java.util.List;

public interface PowerBankService {
    /** 添加移动电源 */
    void addPowerBank(PowerBank powerBank);

    /** 获取所有可用移动电源 */
    List<PowerBank> getAvailablePowerBanks();

    /** 更新移动电源状态 */
    void updatePowerBankStatus(PowerBank powerBank);


}
