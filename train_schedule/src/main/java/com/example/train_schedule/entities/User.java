package com.example.train_schedule.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    private String userName;

    private String userEmail;

    private String userPassword;


    public long getId() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }


    public void setUserName(String username) {
        this.userName = username;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public void setUserPassword(String password) {
        this.userPassword = password;
    }
}
