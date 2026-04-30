package org.legend8883.domain.util;

import org.legend8883.model.Account;
import org.legend8883.model.AccountStorage;
import org.springframework.stereotype.Component;

@Component
public class AccountUtil {
    private final GeneralUtil generalUtil;
    private final AccountStorage accountStorage;

    public AccountUtil(GeneralUtil generalUtil, AccountStorage accountStorage) {
        this.generalUtil = generalUtil;
        this.accountStorage = accountStorage;
    }

    public Integer getValidMoneyAmount(String moneyAmountStr) {
        generalUtil.checkIsInputEmpty(moneyAmountStr);
        int moneyAmount = generalUtil.parseToInt(moneyAmountStr);
        if (moneyAmount < 1) {
            throw new IllegalArgumentException("Money amount must be greater than one");
        }

        return moneyAmount;
    }

    public void checkIsInAccountEnoughMoney(
            double accountMoneyAmount,
            int withdrawMoneyAmount
    ) {
        if (accountMoneyAmount < withdrawMoneyAmount) {
            throw new IllegalArgumentException(String.format(
                    "You can't withdraw more money than you have!%n" +
                            "Your money: %s%n" +
                            "Withdraw amount: %s%n",
                    accountMoneyAmount,
                    withdrawMoneyAmount
            ));
        }
    }

    public Account getAccountByIdWithValidation(String accountIdStr) {
        generalUtil.checkIsInputEmpty(accountIdStr);
        int accountId = generalUtil.parseToInt(accountIdStr);

        return accountStorage.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountId));
    }
}
