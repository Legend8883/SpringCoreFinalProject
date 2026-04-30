package org.legend8883.domain.util;

import org.legend8883.model.Account;
import org.legend8883.model.User;
import org.legend8883.model.UserStorage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AccountUtil {
    private final UserStorage userStorage;

    public AccountUtil(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public Optional<Account> getAccountById(Integer accountId) {
        List<User> users = userStorage.getUsers();
        List<Account> allAccounts = new ArrayList<>();
        Account targetAccount = null;

        for (User user : users) {
            allAccounts.addAll(user.getAccountList());
        }

        for (Account account : allAccounts) {
            if (account.getId().equals(accountId)) {
                targetAccount = account;
            }
        }

       return Optional.ofNullable(targetAccount);
    }
}
