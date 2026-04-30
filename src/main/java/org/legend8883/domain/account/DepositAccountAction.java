package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.AccountUtil;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DepositAccountAction implements CommandAction {
    private final Scanner scanner;
    private final GeneralUtil generalUtil;
    private final AccountUtil accountUtil;

    public DepositAccountAction(
            GeneralUtil generalUtil,
            AccountUtil accountUtil
    ) {
        this.generalUtil = generalUtil;
        this.accountUtil = accountUtil;
        scanner = new Scanner(System.in);
    }


    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

        System.out.println("Enter account id: ");
        String accountIdStr = scanner.nextLine();
        Account account = accountUtil.getAccountByIdWithValidation(accountIdStr);

        System.out.println("Enter deposit amount: ");
        int depositAmount = accountUtil.getValidMoneyAmount(scanner.nextLine());
        double newMoneyAmount = account.getMoneyAmount() + depositAmount;
        account.setMoneyAmount(newMoneyAmount);

        System.out.printf("Deposited %s to account with id: %s. New balance: %s%n", depositAmount, account.getId(), newMoneyAmount);

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_DEPOSIT;
    }
}
