package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.AccountUtil;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.legend8883.model.UserStorage;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class DepositAccountAction implements CommandAction {
    private final Scanner scanner;
    private final UserStorage userStorage;
    private final GeneralUtil generalUtil;
    private final AccountUtil accountUtil;

    public DepositAccountAction(
            UserStorage userStorage,
            GeneralUtil generalUtil, AccountUtil accountUtil
    ) {
        this.userStorage = userStorage;
        this.generalUtil = generalUtil;
        this.accountUtil = accountUtil;
        scanner = new Scanner(System.in);
    }


    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

        String accountIdStr = scanner.nextLine();
        generalUtil.checkIsInputEmpty(accountIdStr);

        int accountId = generalUtil.parseId(accountIdStr);
        Account account = accountUtil.getAccountById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found with id: " + accountId));

    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_DEPOSIT;
    }
}
