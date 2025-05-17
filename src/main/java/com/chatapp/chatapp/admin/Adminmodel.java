package com.chatapp.chatapp.admin;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "adminTable")
@Data
public class Adminmodel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;




    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
}



