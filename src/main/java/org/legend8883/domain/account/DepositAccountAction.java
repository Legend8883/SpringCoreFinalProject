package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.AccountUtil;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.legend8883.model.AccountStorage;
import org.legend8883.model.UserStorage;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DepositAccountAction implements CommandAction {
    private final Scanner scanner;
    private final GeneralUtil generalUtil;
    private final AccountUtil accountUtil;
    private final AccountStorage accountStorage;

    public DepositAccountAction(
            GeneralUtil generalUtil,
            AccountUtil accountUtil,
            AccountStorage accountStorage
    ) {
        this.generalUtil = generalUtil;
        this.accountUtil = accountUtil;
        this.accountStorage = accountStorage;
        scanner = new Scanner(System.in);
    }


    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

        System.out.println("Enter account id: ");
        String accountIdStr = scanner.nextLine();
        generalUtil.checkIsInputEmpty(accountIdStr);
        int accountId = generalUtil.parseToInt(accountIdStr);
        Account account = accountStorage.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountId));

        System.out.println("Enter deposit amount: ");
        int depositAmount = accountUtil.getValidMoneyAmount(scanner.nextLine());
        double newMoneyAmount = account.getMoneyAmount() + depositAmount;
        account.setMoneyAmount(newMoneyAmount);

        System.out.printf("Deposited %s to account with id: %s. New balance: %s%n", depositAmount, accountId, newMoneyAmount);

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_DEPOSIT;
    }
}
