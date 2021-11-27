package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.UserChangePasswordBindingModel;
import com.example.healthyfood.model.binding.UserProfileEditBindingModel;
import com.example.healthyfood.model.binding.UserRegisterBindingModel;
import com.example.healthyfood.model.binding.UserUploadPhotoBindingModel;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.UserChangePasswordServiceModel;
import com.example.healthyfood.model.service.UserProfileEditServiceModel;
import com.example.healthyfood.model.service.UserRegisterServiceModel;
import com.example.healthyfood.model.service.UserUploadPhotoServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.model.view.UserProfileEditViewModel;
import com.example.healthyfood.model.view.UserProfileViewModel;
import com.example.healthyfood.service.CurrentUserProfileViewsService;
import com.example.healthyfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CurrentUserProfileViewsService currentUserProfileViewsService;

    public UserController(ModelMapper modelMapper, UserService userService, CurrentUserProfileViewsService currentUserProfileViewsService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.currentUserProfileViewsService = currentUserProfileViewsService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        boolean notMatchingPasswords = !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword());

        if (bindingResult.hasErrors() || notMatchingPasswords) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            if (notMatchingPasswords) {
                redirectAttributes.addFlashAttribute("notMatchingPasswords", true);
            }

            return "redirect:/users/register";
        }

        UserRegisterServiceModel userRegisterServiceModel = this.modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

        this.userService.registerAndLoginUser(userRegisterServiceModel);

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String invalidLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                       String username,
                               RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("invalidCredentials", true)
                .addFlashAttribute("username", username);

        return "redirect:/users/login";
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model,
                          Principal principal) {

        UserEntity currentUser = this.userService.findByUsername(username);
        UserProfileViewModel userProfileViewModel = this.modelMapper.map(currentUser, UserProfileViewModel.class);

        model.addAttribute("user", userProfileViewModel);
        model.addAttribute("isCurrentUser", principal.getName().equals(username));
        model.addAttribute("views", this.currentUserProfileViewsService.getViewsByAdmins());

        return "profile";
    }

    @GetMapping("/profile/recipes/{username}")
    public String userRecipeList(@PathVariable String username, Model model) {

        List<RecipeSummaryViewModel> userRecipes = this.userService.findAllUserRecipes(username);

        model.addAttribute("recipes", userRecipes)
                .addAttribute("username", username + "'s");

        return "user-recipes";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @GetMapping("/profile/upload-photo/{username}")
    public String uploadPhoto(@PathVariable String username, Model model,
                              Principal principal) {

        if (!model.containsAttribute("userUploadPhotoBindingModel")) {
            model.addAttribute("userUploadPhotoBindingModel", new UserUploadPhotoBindingModel()
                    .setUsername(username));
        }

        return "upload-photo";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @PatchMapping("/profile/upload-photo/{username}")
    public String uploadPhotoConfirm(@PathVariable String username,
                                     @Valid UserUploadPhotoBindingModel userUploadPhotoBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userUploadPhotoBindingModel", userUploadPhotoBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userUploadPhotoBindingModel", bindingResult);

            return "redirect:/users/profile/upload-photo/" + username;
        }

        UserUploadPhotoServiceModel userUploadPhotoServiceModel = this.modelMapper.map(userUploadPhotoBindingModel, UserUploadPhotoServiceModel.class);

        this.userService.uploadProfilePicture(username, userUploadPhotoServiceModel);

        return "redirect:/users/profile/" + username;
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @DeleteMapping("/profile/delete-photo/{username}")
    public String deletePhoto(@PathVariable String username, Principal principal) {

        this.userService.deleteProfilePicture(username);

        return "redirect:/users/profile/" + username;
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @GetMapping("/profile/edit-profile/{username}")
    public String editProfile(@PathVariable String username, Model model,
                              Principal principal) {

        UserProfileEditViewModel userProfileEditViewModel = this.userService.getUserProfileEditViewModel(username);
        UserProfileEditBindingModel userProfileEditBindingModel = this.modelMapper.map(userProfileEditViewModel, UserProfileEditBindingModel.class);

        model.addAttribute("userProfileEditBindingModel", userProfileEditBindingModel);

        return "edit-profile";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @GetMapping("/profile/edit-profile/errors/{username}")
    public String EditProfileErrors(@PathVariable String username,
                                    Principal principal) {

        return "edit-profile";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @PatchMapping("/profile/edit-profile/{username}")
    public String editProfileConfirm(@PathVariable String username,
                                     @Valid UserProfileEditBindingModel userProfileEditBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes,
                                     Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileEditBindingModel", userProfileEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditBindingModel", bindingResult);

            return "redirect:/users/profile/edit-profile/errors/" + username;
        }

        UserProfileEditServiceModel userProfileEditServiceModel = this.modelMapper.map(userProfileEditBindingModel, UserProfileEditServiceModel.class);

        this.userService.editUserProfile(username, userProfileEditServiceModel);

        return "redirect:/users/profile/" + username;
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @GetMapping("/profile/change-password/{username}")
    public String changePassword(@PathVariable String username, Principal principal) {

        return "change-password";
    }

    @PreAuthorize("@userServiceImpl.isCurrentUser(#principal.name, #username)")
    @PatchMapping("/profile/change-password/{username}")
    public String changePasswordConfirm(@PathVariable String username,
                                        @Valid UserChangePasswordBindingModel userChangePasswordBindingModel,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes,
                                        Principal principal) {

        boolean notMatchingPasswords = !userChangePasswordBindingModel.getNewPassword()
                .equals(userChangePasswordBindingModel.getConfirmNewPassword());

        boolean invalidCurrentPassword = !this.userService.isCurrentPasswordValid(username,
                userChangePasswordBindingModel.getCurrentPassword());

        if (bindingResult.hasErrors() || notMatchingPasswords
                || invalidCurrentPassword) {
            redirectAttributes.addFlashAttribute("userChangePasswordBindingModel", userChangePasswordBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userChangePasswordBindingModel", bindingResult);

            if (invalidCurrentPassword) {
                redirectAttributes.addFlashAttribute("invalidCurrentPassword", true);
            } else if (notMatchingPasswords) {
                redirectAttributes.addFlashAttribute("notMatchingPasswords", true);
            }

            return "redirect:/users/profile/change-password/" + username;
        }

        UserChangePasswordServiceModel userChangePasswordServiceModel = this.modelMapper.map(userChangePasswordBindingModel, UserChangePasswordServiceModel.class);

        this.userService.changePassword(username, userChangePasswordServiceModel);

        return "redirect:/users/profile/" + username;
    }

    @ModelAttribute
    public UserChangePasswordBindingModel userChangePasswordBindingModel() {
        return new UserChangePasswordBindingModel();
    }
}
