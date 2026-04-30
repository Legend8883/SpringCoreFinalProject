package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.AccountUtil;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.legend8883.model.AccountStorage;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class WithdrawAccountAction implements CommandAction {
    private final Scanner scanner;
    private final GeneralUtil generalUtil;
    private final AccountUtil accountUtil;
    private final AccountStorage accountStorage;

    public WithdrawAccountAction(
            GeneralUtil generalUtil,
            AccountUtil accountUtil,
            AccountStorage accountStorage
    ) {
        scanner = new Scanner(System.in);
        this.generalUtil = generalUtil;
        this.accountUtil = accountUtil;
        this.accountStorage = accountStorage;
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

        System.out.println("Enter withdraw amount: ");
        int withdrawAmount = accountUtil.getValidMoneyAmount(scanner.nextLine());
        double newMoneyAmount = account.getMoneyAmount() - withdrawAmount;

        if (newMoneyAmount < 0) {
            throw new IllegalArgumentException(String.format(
                    "You can't withdraw more money than you have!%n" +
                            "Your money: %s%n" +
                            "Withdraw amount: %s%n",
                    account.getMoneyAmount(),
                    withdrawAmount
            ));
        }

        account.setMoneyAmount(newMoneyAmount);

        System.out.printf("Withdrawn %s from account with id: %s. New balance: %s%n", withdrawAmount, accountId, newMoneyAmount);

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_WITHDRAW;
    }
}
