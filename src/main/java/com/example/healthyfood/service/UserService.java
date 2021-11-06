package com.example.healthyfood.service;

import com.example.healthyfood.model.service.UserRegisterServiceModel;

public interface UserService {
    boolean isUsernameFree(String username);

    void registerUser(UserRegisterServiceModel userRegisterServiceModel);
}
