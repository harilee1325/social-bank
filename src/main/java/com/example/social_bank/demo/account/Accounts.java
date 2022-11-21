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
    private int debit_card_number;

    @Column()
    private int cvv;

    @Column()
    private String exp;

    @Column()
    private String name;

    public Accounts() {

    }

    public Accounts(Double balance, int user_id, int debit_card_number, int cvv, String exp, String name) {
        super();
        this.balance = balance;
        this.user_id = user_id;
        this.debit_card_number = debit_card_number;
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

    public int getDebit_card_number() {
        return debit_card_number;
    }

    public void setDebit_card_number(int debit_card_number) {
        this.debit_card_number = debit_card_number;
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
