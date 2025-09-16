package com.gymapp.controllers;

import com.gymapp.models.Member;
import com.gymapp.dao.MemberDAO;

import java.time.LocalDate;
import java.util.List;

public class MemberController {
    private final MemberDAO memberDAO;

    public MemberController(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }

    public Member registerMember(String name, String lastName, String phone, String email, LocalDate  joinDate){
        Member member = new Member(0, name, lastName, phone, email, joinDate);
        return memberDAO.create(member);
    }

    public List<Member> listAllMembers(){
        return memberDAO.getAll();
    }

    public boolean updateMember(Member updatedMember){
        return memberDAO.update(updatedMember);
    }

    public boolean deleteMember(int memberId){
        return memberDAO.delete(memberId);
    }

    public Member getMemberById(int id){
        return memberDAO.getById(id);
    }
}
