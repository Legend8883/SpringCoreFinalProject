package org.legend8883.domain.util;

import org.legend8883.model.UserStorage;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
    private final GeneralUtil generalUtil;

    public AccountUtil(GeneralUtil generalUtil) {
        this.generalUtil = generalUtil;
    }

    public Integer getValidMoneyAmount(String moneyAmountStr) {
        generalUtil.checkIsInputEmpty(moneyAmountStr);
        int moneyAmount = generalUtil.parseToInt(moneyAmountStr);
        if (moneyAmount < 1) {
            throw new IllegalArgumentException("Money amount must be greater than one");
        }

        return moneyAmount;
    }
}
