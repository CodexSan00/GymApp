package com.gymapp.models;

import java.time.LocalDate;

public class Member {
    private int id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private LocalDate joinDate;

    public Member(){}

    public Member(int id, String name, String lastName, String phone, String email, LocalDate joinDate){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.joinDate = joinDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String tel) {
        this.phone = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
    @Override
    public String toString(){
        return("\nName: " + name + "\nLastname: " + lastName +
                "\nPhone: " + phone + "\nEmail: " + email + "\nJoinDate: " + joinDate);
    }
}
