package com.example.dell.freerackapp.model;

public class User {

    String mUserEmail;
    String mUserPassword;

    public User(String email, String password){
        mUserEmail = email;
        mUserPassword = password;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public String getUserPassword() {
        return mUserPassword;
    }


}
