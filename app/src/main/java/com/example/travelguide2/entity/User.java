package com.example.travelguide2.entity;

import java.util.Date;

public class User {
    private int userId;
    private String userName;
    private String userPw;
    private String gender;
    private String email;
    private String create_time;
    private String birthday;
    private String head_portrait;
    private String description;



    public User(){

    }

    public User(int userId, String userName, String userPw, String gender, String email, String create_time, String birthday, String head_portrait, String description) {
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
        this.gender = gender;
        this.email = email;
        this.create_time = create_time;
        this.birthday = birthday;
        this.head_portrait = head_portrait;
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHead_portrait() {
        return head_portrait;
    }

    public void setHead_portrait(String head_portrait) {
        this.head_portrait = head_portrait;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
