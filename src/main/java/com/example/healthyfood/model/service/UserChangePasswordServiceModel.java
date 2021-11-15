package com.example.healthyfood.model.service;

public class UserChangePasswordServiceModel {

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public UserChangePasswordServiceModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }
}
