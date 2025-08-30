package com.gymapp.models;

import java.time.LocalDate;

public class Fee {
    private int id;
    private double amount;
    private Status status;
    private int membershipId;
    private LocalDate dueDate;
    private LocalDate startDate;

    public enum Status { PENDING, PAID, OVERDUE }

    public Fee(){}

    public Fee (int id, int membershipId, double amount, Status status, LocalDate startDate, LocalDate dueDate){
        this.id = id;
        this.membershipId = membershipId;
        this.amount = amount;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
    @Override
    public String toString() {
        return "Fee{" +
                "id=" + id +
                ", membershipId=" + membershipId +
                ", amount=" + amount +
                ", status=" + status +
                ", startDate=" + startDate +
                ", dueDate=" + dueDate +
                '}';
    }
}
