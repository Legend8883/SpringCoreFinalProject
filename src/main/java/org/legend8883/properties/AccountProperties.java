package org.legend8883.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {
    private final double defaultBalance;
    private final double transferCommission;

    public AccountProperties(
            @Value("${account.default-balance}")
            double defaultBalance,
            @Value("${account.transfer-commission}")
            double transferCommission
    ) {
        this.defaultBalance = defaultBalance;
        this.transferCommission = transferCommission;
    }

    public double getDefaultBalance() {
        return defaultBalance;
    }

    public double getTransferCommission() {
        return transferCommission;
    }
}
