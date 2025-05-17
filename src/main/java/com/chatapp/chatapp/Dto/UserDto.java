package com.chatapp.chatapp.Dto;

import jakarta.persistence.Column;

public class UserDto {
    private Integer userId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    private String name;

    private Integer mobile;

}
