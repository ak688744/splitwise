package com.splitwise.demo.models;

public class CreateUser {
    String userName;

    public CreateUser(String userName) {

        this.userName = userName;
    }

    public CreateUser() {


    }

    public String getUserName() {



        return userName;
    }

    public void setUserName(String userName) {


        this.userName = userName;
    }
}
