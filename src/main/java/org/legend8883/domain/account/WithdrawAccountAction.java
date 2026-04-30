package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.AccountUtil;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class WithdrawAccountAction implements CommandAction {
    private final Scanner scanner;
    private final GeneralUtil generalUtil;
    private final AccountUtil accountUtil;

    public WithdrawAccountAction(
            GeneralUtil generalUtil,
            AccountUtil accountUtil
    ) {
        scanner = new Scanner(System.in);
        this.generalUtil = generalUtil;
        this.accountUtil = accountUtil;
    }

    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

        System.out.println("Enter account id: ");
        String accountIdStr = scanner.nextLine();
        Account account = accountUtil.getAccountByIdWithValidation(accountIdStr);

        System.out.println("Enter withdraw amount: ");
        int withdrawAmount = accountUtil.getValidMoneyAmount(scanner.nextLine());

        accountUtil.checkIsInAccountEnoughMoney(account.getMoneyAmount(), withdrawAmount);

        double newMoneyAmount = account.getMoneyAmount() - withdrawAmount;
        account.setMoneyAmount(newMoneyAmount);

        System.out.printf("Withdrawn %s from account with id: %s. New balance: %s%n", withdrawAmount, account.getId(), newMoneyAmount);

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_WITHDRAW;
    }
}
