package com.example.social_bank.demo.account;

import javax.persistence.*;

@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column()
    private Double balance;

    @Column()
    private int user_id;

    @Column()
    private String credit_card_number;

    @Column()
    private String  limit;

    @Column()
    private int cvv;

    @Column()
    private String exp;

    @Column()
    private String name;

    public Accounts() {

    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public Accounts(Double balance, int user_id, String credit_card_number, int cvv, String exp, String name) {
        super();
        this.balance = balance;
        this.user_id = user_id;
        this.credit_card_number = credit_card_number;
        this.cvv = cvv;
        this.exp = exp;
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public void setCredit_card_number(String credit_card_number) {
        this.credit_card_number = credit_card_number;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
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
}
