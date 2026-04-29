package org.legend8883.service.user;

import org.legend8883.console.CommandAction;
import org.legend8883.console.CommandType;
import org.legend8883.model.Account;
import org.legend8883.model.User;
import org.legend8883.properties.AccountProperties;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CreateUserAction implements CommandAction {
    private final AccountProperties accountProperties;
    private final UserStorage userStorage;
    private final Scanner scanner;

    public CreateUserAction(
            AccountProperties accountProperties,
            UserStorage userStorage
    ) {
        this.accountProperties = accountProperties;
        this.userStorage = userStorage;
        scanner = new Scanner(System.in);
    }

    @Override
    public void execute() {
        System.out.println("Enter login: ");
        String login = scanner.nextLine();
        checkIsLoginEmpty(login);

        User user = new User(login);
        Account firstAccount = new Account(user.getId(), accountProperties.getDefaultBalance());
        user.addAccount(firstAccount);
        userStorage.addUser(user);

        System.out.println("User created: " + user);
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.USER_CREATE;
    }

    private void checkIsLoginEmpty(String login) {
        if (login.trim().isEmpty()) {
            throw new IllegalArgumentException("Login must not be empty");
        }
    }
}
