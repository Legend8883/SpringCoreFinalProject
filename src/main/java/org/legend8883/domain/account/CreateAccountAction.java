package org.legend8883.domain.account;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.domain.util.GeneralUtil;
import org.legend8883.model.Account;
import org.legend8883.model.AccountStorage;
import org.legend8883.model.User;
import org.legend8883.model.UserStorage;
import org.legend8883.properties.AccountProperties;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreateAccountAction implements CommandAction {
    private final Scanner scanner;
    private final AccountProperties accountProperties;
    private final UserStorage userStorage;
    private final GeneralUtil generalUtil;
    private final AccountStorage accountStorage;

    public CreateAccountAction(
            AccountProperties accountProperties,
            UserStorage userStorage,
            GeneralUtil generalUtil, AccountStorage accountStorage
    ) {
        this.accountProperties = accountProperties;
        this.userStorage = userStorage;
        this.generalUtil = generalUtil;
        this.accountStorage = accountStorage;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        generalUtil.checkIsUsersExist();

        System.out.println("Enter user id: ");
        String userIdStr = scanner.nextLine();
        generalUtil.checkIsInputEmpty(userIdStr);

        int userId = generalUtil.parseToInt(userIdStr);
        User user = userStorage.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));

        Account account = new Account(user.getId(), accountProperties.getDefaultBalance());
        accountStorage.addAccount(account);

        System.out.printf(
                "Account for user %s created: %s%n",
                user.getLogin(), account
        );
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.ACCOUNT_CREATE;
    }
}
