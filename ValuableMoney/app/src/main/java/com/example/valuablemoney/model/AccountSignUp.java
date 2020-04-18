package com.example.valuablemoney.model;

public class AccountSignUp {
    private int id;
    private String username;
    private String pass1;
    private String pass2;

    public AccountSignUp(int id, String username, String pass1, String pass2) {
        this.id = id;
        this.username = username;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }

    public AccountSignUp(String username, String pass1, String pass2) {
        this.username = username;
        this.pass1 = pass1;
        this.pass2 = pass2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }
}
