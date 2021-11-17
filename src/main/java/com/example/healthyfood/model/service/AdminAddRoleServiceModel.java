package com.example.healthyfood.model.service;

import com.example.healthyfood.model.entity.enums.UserRoleEnum;

public class AdminAddRoleServiceModel {

    private String username;
    private UserRoleEnum userRole;

    public String getUsername() {
        return username;
    }

    public AdminAddRoleServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public AdminAddRoleServiceModel setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
