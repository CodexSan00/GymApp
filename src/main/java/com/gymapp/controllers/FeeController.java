package com.gymapp.controllers;

import com.gymapp.models.Fee;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class FeeController {
    List<Fee> feeList = new ArrayList<>();
    int feeCounter = 0;

    public Fee generateFee(int membershipId, double amount){
        Date startDate = new Date();
        Date dueDate = new Date();
        Fee fee = new Fee(feeCounter++, membershipId, amount, Fee.Status.PENDING, startDate, dueDate);
        feeList.add(fee);
        System.out.println(fee);
        return fee;
    }

    public void getAllFees(){ //debugggggg
        if(feeList.isEmpty()) {
            System.out.println("List is empty");
        }else{
            for(Fee f: feeList){
                System.out.println(f);
            }
        }
    }


    public List<Fee> listFeesByMembership(int membershipId){
        List<Fee> result = new ArrayList<>();
        for(Fee f : feeList){
                if(f.getMembershipId() == membershipId){
                    result.add(f);
                }
        }
        System.out.println(result);
        return result;
    }

    public Fee getFeeById(int feeId){
        for(Fee f: feeList){
            if(f.getId() == feeId){
                System.out.println(f);
                return f;
            }
        }
        return null;
    }
    public Fee updateFeeAmount(int feeId, double amount){
        for(Fee f: feeList){
            if(f.getId() == feeId){
                f.setAmount(amount);
                System.out.println(f);
                return f;
            }
        }

        return null;
    }
    public void deleteFee(int feeId){
        for(Fee f: feeList){
            if(f.getId() == feeId){
                feeList.remove(f);
                System.out.println("Fee removed.");
            }
        }
    }
    }

