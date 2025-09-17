package com.gymapp.controllers;

import com.gymapp.dao.FeeDAO;
import com.gymapp.models.Fee;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class FeeController {
    private final FeeDAO feeDAO;

    public FeeController(FeeDAO feeDAO) {
        this.feeDAO = feeDAO;
    }

    public Fee generateFee(int membershipId, double amount) {
        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = LocalDate.now().plusMonths(1);
        Fee fee = new Fee(0, membershipId, amount, Fee.Status.PENDING, startDate, dueDate);
        return feeDAO.create(fee);
    }

    public List<Fee> getAllFees() {
        return feeDAO.getAll();
    }


    public List<Fee> listFeesByMembership(int membershipId) {
        return feeDAO.getByMembership(membershipId);
    }

    public Fee getFeeById(int feeId) {
        return feeDAO.getById(feeId);
    }

    public boolean updateFee(Fee fee) {
        return feeDAO.update(fee);
    }

    public boolean deleteFee(int feeId) {
        return feeDAO.delete(feeId);
    }

    public List<Fee> listAllFees(){
        return feeDAO.getAll();
    }
}

