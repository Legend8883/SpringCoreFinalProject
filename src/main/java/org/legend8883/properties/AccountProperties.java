package org.legend8883.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountProperties {
    private final Double defaultBalance;
    private final Double transferCommission;

    public AccountProperties(
            @Value("${account.default-balance}")
            Double defaultBalance,
            @Value("${account.transfer-commission}")
            Double transferCommission
    ) {
        this.defaultBalance = defaultBalance;
        this.transferCommission = transferCommission;
    }

    public Double getDefaultBalance() {
        return defaultBalance;
    }

    public Double getTransferCommission() {
        return transferCommission;
    }
}
