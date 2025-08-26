package com.gymapp.models;

import java.util.Date;

public class Membership {
    private int id; // temporary, when the database is created it must be removed
    private int memberId;
    private String planType;
    private Date startDate;
    private Date endDate;
    private Status status;

    public enum Status {ACTIVE, EXPIRED, CANCELLED};

    public Membership(){}
    public Membership(int id, int memberId, String planType, Date startDate){
        this.id = id;
        this.memberId = memberId;
        this.planType = planType;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", planType='" + planType + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }

    }

