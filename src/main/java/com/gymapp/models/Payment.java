package com.gymapp.models;

import java.time.LocalDate;



public class Payment {
    private int id;
    private double amount;
    private int feeId;
    private LocalDate paymentDate;
    private String reference;
    private Method paymentMethod;
    private Status status;

    public enum Method { cash, credit_card, transfer, bank_transfer }
    public enum Status { COMPLETED, PENDING, FAILED }

    public Payment(){}
    public Payment(int id, int feeId, double amount, LocalDate paymentDate, Method method, Status status){
        this.id = id;
        this.feeId = feeId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = method;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFeeId() {
        return feeId;
    }

    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Method getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Method paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", feeId=" + feeId +
                ", paymentDate=" + paymentDate +
                ", reference='" + reference + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                '}';
    }
}
