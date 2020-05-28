package com.example.modelpaper2020;

public class User {

    private String id;
    private String username;
    private String password;
    private String dob;
    private String gender;

    public User() {
    }

    public User(String id, String username, String password, String dob, String gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
