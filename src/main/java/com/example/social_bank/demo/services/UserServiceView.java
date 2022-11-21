package com.example.social_bank.demo.services;

public class UserServiceView {

    private String userId;
    private String serviceId;
    private String status;

    private String typeId;

    public UserServiceView(String userId, String serviceId, String status) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
