package com.example.social_bank.demo.services;

import javax.persistence.*;

@Entity
public class ServicesTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String service_name;

    public ServicesTable(String service_name) {
        super();
        this.service_name = service_name;
    }

    public ServicesTable() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }
}
