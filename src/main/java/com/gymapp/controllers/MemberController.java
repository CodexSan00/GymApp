package com.gymapp.controllers;

import com.gymapp.models.Member;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class MemberController {
    private List<Member> members = new ArrayList<>(); //This will be removed in the future
    private int nextId = -1; //just for simulate auto-increment

    public Member registerMember(String name, String lastName, String phone, String email, Date joinDate){
        Member m = new Member(nextId++, name, lastName, phone, email, joinDate);
        members.add(m);
        return m;
    }
    public List<Member> listAllMembers(){
        return new ArrayList<>(members);
    }
    public boolean editMember(int id, String name, String lastName, String phone, String email, Date joinDate){
        for(Member m: members){
            if(m.getId() == id){
                m.setName(name);
                m.setLastName(lastName);
                m.setPhone(phone);
                m.setEmail(email);
                m.setJoinDate(joinDate);
                return true;
            }
        }
        return false;
    }
    public boolean deleteMember(int memberId){
        for(int i = 0; i < members.size(); i++){
            if(members.get(i).getId() == memberId){
                members.remove(i);
                return true;
            }
        }
        return false;
    }
}
