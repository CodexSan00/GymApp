package com.gymapp.service;

import com.gymapp.dao.PaymentDAO;
import com.gymapp.models.Payment;

import java.time.LocalDate;
import java.util.List;

public class PaymentService {
    private final PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO){
        this.paymentDAO = paymentDAO;
    }


    public List<Payment> listAllPayments() {
        return paymentDAO.getAll();
    }

    public Payment generatePayment(int feeId, double amount, Payment.Method method) {
        LocalDate paymentDate = LocalDate.now();
        //If the payment method is not cash, there may be delays.
        // Until the payment is credited, it will remain PENDING.
        Payment.Status status = (method == Payment.Method.CASH)
                ? Payment.Status.COMPLETED
                : Payment.Status.PENDING;

        Payment payment = new Payment(0, feeId, amount, paymentDate, method, status);
        return paymentDAO.create(payment);
    }

    public List<Payment> getPaymentByFee(int feeId) {
        return paymentDAO.getByFee(feeId);
    }

    public Payment getPaymentById(int paymentId){
        return paymentDAO.getById(paymentId);
    }

    public boolean updatePayment(Payment payment){
        return paymentDAO.update(payment);
    }

    public boolean deletePayment(int paymentId){
        return paymentDAO.delete(paymentId);
    }
}

