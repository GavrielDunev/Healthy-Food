package com.example.healthyfood.model.view;

public class UserProfileViewModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profilePictureUrl;

    public String getUsername() {
        return username;
    }

    public UserProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public UserProfileViewModel setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
