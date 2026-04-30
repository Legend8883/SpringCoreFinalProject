package org.legend8883.domain.user;

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
public class CreateUserAction implements CommandAction {
    private final AccountProperties accountProperties;
    private final UserStorage userStorage;
    private final GeneralUtil generalUtil;
    private final AccountStorage accountStorage;
    private final Scanner scanner;

    public CreateUserAction(
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
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        generalUtil.checkIsInputEmpty(login);
        checkIsLoginExists(login);

        User user = new User(login);
        Account firstAccount = new Account(user.getId(), accountProperties.getDefaultBalance());
        accountStorage.addAccount(firstAccount);
        userStorage.addUser(user);

        System.out.println("User created: " + user + " with account " + firstAccount);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.USER_CREATE;
    }

    private void checkIsLoginExists(String login) {
        for (User user : userStorage.getUsers()) {
            if (user.getLogin().equals(login)) {
                throw new IllegalArgumentException("User with this login already exists");
            }
        }
    }
}
