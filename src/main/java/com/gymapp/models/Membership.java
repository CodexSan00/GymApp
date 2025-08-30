package com.gymapp.models;

import java.time.LocalDate;

public class Membership {
    private int id;
    private int memberId;
    private String planType;
    private LocalDate startDate;
    private Status status;

    public enum Status {ACTIVE, EXPIRED, CANCELLED};

    public Membership(){}
    public Membership(int id, int memberId, String planType, LocalDate startDate){
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
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
                ", status=" + status +
                '}';
    }

    }

