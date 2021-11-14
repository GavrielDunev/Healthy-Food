package com.example.healthyfood.service;

import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.UserRegisterServiceModel;
import com.example.healthyfood.model.service.UserUploadPhotoServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean isUsernameFree(String username);

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    UserEntity findByUsername(String username);

    List<RecipeSummaryViewModel> findAllUserRecipes(String username);

    void uploadProfilePicture(String username, UserUploadPhotoServiceModel userUploadPhotoServiceModel) throws IOException;

    void deleteProfilePicture(String username);
}
