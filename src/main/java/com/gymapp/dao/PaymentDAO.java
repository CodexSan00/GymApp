package com.gymapp.dao;

import com.gymapp.models.Payment;

import java.util.List;

public interface PaymentDAO {
    Payment create(Payment payment);
    List<Payment> getAll();
    Payment getById(int paymentId);
    List<Payment> getByFee(int feeId);
    boolean update(Payment payment);
    boolean delete(int paymentId);

}
