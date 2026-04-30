package org.legend8883.model;

import java.util.Objects;

public class Account {
    private static int counter = 1;
    private final Integer id;
    private Integer userId;
    private Double moneyAmount;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(userId, account.userId) && Objects.equals(moneyAmount, account.moneyAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, moneyAmount);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

    public Account(Integer userId, Double moneyAmount) {
        id = counter++;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Double moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
