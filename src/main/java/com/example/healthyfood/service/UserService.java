package com.example.healthyfood.service;

import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.*;
import com.example.healthyfood.model.view.AdminAddRoleViewModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.model.view.UserProfileEditViewModel;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean isUsernameFree(String username);

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    UserEntity findByUsername(String username);

    List<RecipeSummaryViewModel> findAllUserRecipes(String username);

    void uploadProfilePicture(String username, UserUploadPhotoServiceModel userUploadPhotoServiceModel) throws IOException;

    void deleteProfilePicture(String username);

    UserProfileEditViewModel getUserProfileEditViewModel(String username);

    void editUserProfile(String username, UserProfileEditServiceModel userProfileEditServiceModel);

    boolean isCurrentPasswordValid(String username, String currentPassword);

    void changePassword(String username, UserChangePasswordServiceModel userChangePasswordServiceModel);

    boolean isCurrentUser(String currentUserUsername, String username);

    List<AdminAddRoleViewModel> getAllUserUsernamesWithoutCurrent(String currentUser);

    void addRole(AdminAddRoleServiceModel addRoleServiceModel);
}
