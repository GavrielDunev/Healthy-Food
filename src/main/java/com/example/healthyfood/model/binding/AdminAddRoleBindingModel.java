package com.example.healthyfood.model.binding;

import com.example.healthyfood.model.entity.enums.UserRoleEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AdminAddRoleBindingModel {

    @NotBlank
    private String username;
    @NotNull
    private UserRoleEnum userRole;

    public String getUsername() {
        return username;
    }

    public AdminAddRoleBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserRoleEnum getUserRole() {
        return userRole;
    }

    public AdminAddRoleBindingModel setUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }
}
