package org.legend8883.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountStorage {
    private final Map<Integer, Account> accounts;

    public AccountStorage() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
    }

    public Optional<Account> getAccountById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public List<Account> getAccountListByUserId(int userId) {
        return accounts.values().stream()
                .filter(account -> account.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
