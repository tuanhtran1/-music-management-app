package com.example.quanlyamnhac.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    String email,phone,username,password,avatar;

    public UserModel() {
    }

    public UserModel(String email, String phone, String username, String password, String avatar) {
        this.email = email;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}