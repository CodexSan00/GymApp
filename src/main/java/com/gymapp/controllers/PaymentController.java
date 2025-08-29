package com.gymapp.controllers;

import com.gymapp.models.Payment;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class PaymentController {
    int id = -1; //Simulates auto-increment
    List<Payment> payments = new ArrayList<>(); //Simulates DB

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public Payment generatePayment(int feeId, double amount, Payment.Method method) {
        Date paymentDate = new Date();
        Payment p = new Payment(id++, feeId, amount, paymentDate, method);
        payments.add(p);
        return p;
    }

    public List<Payment> getPaymentByFee(int feeId) {
        List<Payment> paymentsByFee = new ArrayList<>();
        for (Payment p : payments) {
            if (p.getFeeId() == feeId) {
                paymentsByFee.add(p);
            }
        }
        return paymentsByFee;
    }

    public Payment getPaymentById(int paymentId){
        for(Payment p: payments){
            if(p.getId() == paymentId){
                return p;
            }
        }
        return null;
    }

    public boolean updatePaymentStatus(int paymentId, Payment.Status status){
        for(Payment p: payments){
            if(p.getId() == paymentId){
                p.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public boolean deletePayment(int paymentId){
        for(int i = 0; i < payments.size(); i++){
            if(payments.get(i).getId() == paymentId){
                payments.remove(i);
                return true;
            }
        }
        return false;
    }
}

