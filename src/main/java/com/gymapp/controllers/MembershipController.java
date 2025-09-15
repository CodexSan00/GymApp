package com.gymapp.controllers;

import com.gymapp.dao.MembershipDAO;
import com.gymapp.models.Membership;
import com.gymapp.models.Member;

import java.time.LocalDate;
import java.util.List;


public class MembershipController {

    private final MembershipDAO membershipDAO;

    public MembershipController(MembershipDAO membershipDAO){
        this.membershipDAO = membershipDAO;
    }

    public void registerMembership(Member member, String planType, LocalDate startDate){
        Membership membership = new Membership(0, member.getId(), planType, startDate, Membership.Status.ACTIVE);
        membershipDAO.create(membership);
    }

    public List<Membership> listAllMemberships() {
        return membershipDAO.getAll();
    }

    public Membership getMembershipByMember(int memberId){
        return membershipDAO.getByMemberId(memberId);
    }

    public boolean updateMembership(Membership updatedMembership){
        return membershipDAO.update(updatedMembership);
    }

    public boolean deleteMembership(int membershipId){
        return membershipDAO.delete(membershipId);
    }

}
