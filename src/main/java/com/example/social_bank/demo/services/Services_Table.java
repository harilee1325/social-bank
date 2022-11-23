package com.example.social_bank.demo.services;

import javax.persistence.*;

@Entity
public class Services_Table {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String service_name;

    @Column
    private String description;

    public Services_Table(String service_name, String desc) {
        super();
        this.service_name = service_name;
        this.description = desc;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public Services_Table() {
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
