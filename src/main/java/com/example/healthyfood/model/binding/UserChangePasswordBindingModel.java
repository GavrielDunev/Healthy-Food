package com.example.healthyfood.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserChangePasswordBindingModel {

    @NotBlank
    @Size(min = 8, max = 30)
    private String currentPassword;
    @NotBlank
    @Size(min = 8, max = 30)
    private String newPassword;
    @NotBlank
    @Size(min = 8, max = 30)
    private String confirmNewPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public UserChangePasswordBindingModel setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
        return this;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public UserChangePasswordBindingModel setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public UserChangePasswordBindingModel setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
        return this;
    }
}
