package com.gymapp.controllers;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import com.gymapp.models.Membership;
import com.gymapp.models.Member;

public class MembershipController {
    int nextId = -1;
    private List<Membership> membershipArray = new ArrayList<>(); //Simulates DAO, this will be removed in the future

    public void assignMembership(Member member, String planType, LocalDate startDate){
        Membership membership = new Membership(nextId++, member.getId(), planType, startDate);
        membership.setStatus(Membership.Status.ACTIVE);
        membershipArray.add(membership);
    }

    public List<Membership> listAllMemberships() {
        return new ArrayList<>(membershipArray);
    }

    public Membership getMembershipByMember(int memberId){
        for(Membership m: membershipArray){
            if(m.getMemberId() == memberId){
                return m;
            }
        }
        return null;
    }

    public boolean updateMembership(int membershipId, String planType, LocalDate startDate, Membership.Status status){
        for(Membership m: membershipArray){
            if(m.getId() == membershipId){
                m.setPlanType(planType);
                m.setStartDate(startDate);
                m.setStatus(status);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMembership(int membershipId){
        for(Membership m: membershipArray){
            if(m.getId() == membershipId){
                membershipArray.remove(m);
                return true;
            }
        }
        System.out.println("Membership not found.");
        return false;
    }

}
