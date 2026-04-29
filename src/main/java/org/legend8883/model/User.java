package org.legend8883.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int counter = 0;
    private final Integer id;
    private String login;
    private final List<Account> accountList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }

    public User(String login) {
        id = counter++;
        this.login = login;
        accountList = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }
}
