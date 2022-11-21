package com.example.social_bank.demo.services;

import javax.persistence.*;

@Entity
public class UserServices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private Integer service_id;

    @Column
    private Integer user_id;

    @Column
    private String status;

    public UserServices(Integer service_id, Integer user_id, String status) {
        super();
        this.service_id = service_id;
        this.user_id = user_id;
        this.status = status;
    }

    public UserServices() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getService_id() {
        return service_id;
    }

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
