package com.example.healthyfood.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserProfileEditBindingModel {

    @NotNull
    @Size(min = 2, max = 20)
    private String firstName;
    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;
    @NotNull
    @Email
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public UserProfileEditBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileEditBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileEditBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
