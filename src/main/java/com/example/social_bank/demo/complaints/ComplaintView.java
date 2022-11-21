package com.example.social_bank.demo.complaints;

public class ComplaintView {
    private String complaint;
    private String userId;
    private String status;


    public ComplaintView() {
    }

    public ComplaintView(String complaint, String userId, String status) {
        this.complaint = complaint;
        this.userId = userId;
        this.status = status;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
