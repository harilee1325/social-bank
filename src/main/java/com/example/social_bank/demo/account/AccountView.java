package com.example.social_bank.demo.account;

public class AccountView {
    private String balance;
    private String userId;

    private String debitCard;

    private String exp;

    private String name;

    private String cvv;


    public AccountView(String balance, String userId, String debitCard, String exp, String name, String cvv) {
        this.balance = balance;
        this.userId = userId;
        this.debitCard = debitCard;
        this.exp = exp;
        this.name = name;
        this.cvv = cvv;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
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
