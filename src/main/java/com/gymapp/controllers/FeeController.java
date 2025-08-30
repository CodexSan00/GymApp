package com.gymapp.controllers;

import com.gymapp.models.Fee;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class FeeController {
    List<Fee> feeList = new ArrayList<>();
    int feeCounter = -1; // simulates auto-increment

    public Fee generateFee(int membershipId, double amount){
        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = LocalDate.now();
        Fee fee = new Fee(feeCounter++, membershipId, amount, Fee.Status.PENDING, startDate, dueDate);
        feeList.add(fee);
        return fee;
    }

    public List<Fee> getAllFees(){
        return new ArrayList<>(feeList);
    }


    public List<Fee> listFeesByMembership(int membershipId){
        List<Fee> feeByMember = new ArrayList<>();
        for(Fee f : feeList){
                if(f.getMembershipId() == membershipId){
                    feeByMember.add(f);
                }
        }
        return feeByMember;
    }

    public Fee getFeeById(int feeId){
        for(Fee f: feeList){
            if(f.getId() == feeId){
                return f;
            }
        }
        return null;
    }
    public Fee updateFeeAmount(int feeId, double amount){
        for(Fee f: feeList){
            if(f.getId() == feeId){
                f.setAmount(amount);
                return f;
            }
        }

        return null;
    }
    public boolean deleteFee(int feeId){
        for(int i = 0; i < feeList.size() ; i++){
            if(feeList.get(i).getId() == feeId){
                feeList.remove(i);
                return true;
            }
        }
        return false;
    }
    }

