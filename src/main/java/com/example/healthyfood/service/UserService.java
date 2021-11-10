package com.example.healthyfood.service;

import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.UserRegisterServiceModel;

public interface UserService {
    boolean isUsernameFree(String username);

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    UserEntity findByUsername(String username);
}
