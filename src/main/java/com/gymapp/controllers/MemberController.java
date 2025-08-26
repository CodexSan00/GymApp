package com.gymapp.controllers;

import com.gymapp.models.Member;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class MemberController {
    private List<Member> members = new ArrayList<>(); //This will be removed in the future
    private int nextId = 0; //just for simulate auto-increment

    public void register(String name, String lastName, String phone, String email, Date joinDate){
        Member m = new Member(nextId++, name, lastName, phone, email, joinDate);
        members.add(m);
        System.out.println("\nMember " + m.getName() +" registered!");
        m.toString();
    }
    public void listAllMembers(){
        if(members.isEmpty()){
            System.out.println("List is empty.");
        } else {
            for (Member m: members){
                m.toString();
            }
        }
    }
    public void edit(int id, String name, String lastName, String phone, String email, Date joinDate){
        for(Member m: members){
            if(m.getId() == id){
                m.setName(name);
                m.setLastName(lastName);
                m.setPhone(phone);
                m.setEmail(email);
                m.setJoinDate(joinDate);
            }
        }
    }
    public void delete(int id){
        boolean toRemove = members.removeIf(members -> members.getId() == id);
        if(toRemove){
            System.out.println("Member removed.");
        } else {
            System.out.println("Member not found.");
        }
    }
}
