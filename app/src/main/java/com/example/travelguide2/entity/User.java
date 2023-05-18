package com.example.travelguide2.entity;

public class User {
    private int userId;
    private String userName;
    private String userPw;

    public User(){

    }

    public User(int userId, String userName, String userPw) {
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
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
}
