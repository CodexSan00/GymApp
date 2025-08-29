package com.gymapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gymapp.models.Membership;
import com.gymapp.models.Member;

public class MembershipController {
    int nextId = -1;
    private List<Membership> membershipArray = new ArrayList<>(); //Simulates DAO, this will be removed in the future

    public void assignMembership(Member member, String planType, Date startDate){
        Membership membership = new Membership(nextId++, member.getId(), planType, startDate);
        membership.setStatus(Membership.Status.ACTIVE);
        membershipArray.add(membership);
        System.out.println("Membership assigned to: " + member.getName());
    }
    public void listAllMemberships() {
        if(membershipArray.isEmpty()){
            System.out.println("List is empty.");
        } else {
            for(Membership m: membershipArray){
                System.out.println(m.toString());
            }
        }
    }
    public Membership listMembershipByMember(int memberId){
        if(membershipArray.isEmpty()){
            System.out.println("List is empty.");
            return null;
        } else {
            for(Membership m: membershipArray){
                if(m.getMemberId() == memberId){
                    System.out.println(m);
                    return m;
                }
            }
        }
        System.out.println("Member has no membership.");
        return null;
    }
    public boolean cancelMembership(int membershipId){
        for(Membership m: membershipArray){
            if(m.getId() == membershipId){
                m.setStatus(Membership.Status.CANCELLED);
                return true;
            }
        }
        System.out.println("Membership not found.");
        return false;
    }


}
