package com.example.social_bank.demo.complaints;

import javax.persistence.*;

@Entity
public class Complaints {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column
    private String complaint;

    @Column
    private String user_id;

    @Column
    private String date;

    @Column
    private String status;

    public Complaints() {
    }

    public Complaints(String complaint, String user_id, String date, String status) {
        super();
        this.complaint = complaint;
        this.user_id = user_id;
        this.date = date;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
