package com.example.zjy.dbhelper;

/**
 * Created by zjy on 2017/3/20.
 */

public class User {
    private String userPhone;
    private String userPassword;

    public User(String userPhone, String userPassword) {
        this.userPhone = userPhone;
        this.userPassword = userPassword;
    }

    public User() {
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
