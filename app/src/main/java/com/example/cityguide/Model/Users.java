package com.example.cityguide.Model;

public class Users {

    private String name, email, phoneNo, username;

    public Users()
    {

    }

    public Users(String name, String email, String phoneNo, String username) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
