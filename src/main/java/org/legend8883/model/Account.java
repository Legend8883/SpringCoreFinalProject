package org.legend8883.model;

public class Account {
    private static int counter = 1;
    private final Integer id;
    private Integer userId;
    private Double moneyAmount;

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
