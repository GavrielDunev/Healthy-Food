package com.example.healthyfood.model.service;

public class UserProfileEditServiceModel {

    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public UserProfileEditServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileEditServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileEditServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
