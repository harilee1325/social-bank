package com.example.social_bank.demo.account;

public class AccountView {
    private String balance;
    private String userId;

    private String debitCard;

    public AccountView(String balance, String userId, String debitCard) {
        this.balance = balance;
        this.userId = userId;
        this.debitCard = debitCard;
    }

    public AccountView() {
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(String debitCard) {
        this.debitCard = debitCard;
    }
}
