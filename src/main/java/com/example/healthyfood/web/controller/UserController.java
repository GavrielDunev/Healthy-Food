package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.UserProfileEditBindingModel;
import com.example.healthyfood.model.binding.UserRegisterBindingModel;
import com.example.healthyfood.model.binding.UserUploadPhotoBindingModel;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.UserProfileEditServiceModel;
import com.example.healthyfood.model.service.UserRegisterServiceModel;
import com.example.healthyfood.model.service.UserUploadPhotoServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.model.view.UserProfileEditViewModel;
import com.example.healthyfood.model.view.UserProfileViewModel;
import com.example.healthyfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

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
    public String profile(@PathVariable String username, Model model) {

        UserEntity currentUser = this.userService.findByUsername(username);
        UserProfileViewModel userProfileViewModel = this.modelMapper.map(currentUser, UserProfileViewModel.class);

        model.addAttribute("user", userProfileViewModel);

        return "profile";
    }

    @GetMapping("/profile/recipes/{username}")
    public String userRecipeList(@PathVariable String username, Model model) {

        List<RecipeSummaryViewModel> userRecipes = this.userService.findAllUserRecipes(username);

        model.addAttribute("recipes", userRecipes)
                .addAttribute("username", username + "'s");

        return "user-recipes";
    }

    @GetMapping("/profile/upload-photo/{username}")
    public String uploadPhoto(@PathVariable String username, Model model) {

        if (!model.containsAttribute("userUploadPhotoBindingModel")) {
            model.addAttribute("userUploadPhotoBindingModel", new UserUploadPhotoBindingModel());
        }

        return "upload-photo";
    }

    @PostMapping("/profile/upload-photo/{username}")
    public String uploadPhotoConfirm(@PathVariable String username,
                                     @Valid UserUploadPhotoBindingModel userUploadPhotoBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userUploadPhotoBindingModel", userUploadPhotoBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userUploadPhotoBindingModel", bindingResult);

            return "redirect:/users/profile/upload-photo/" + username;
        }

        UserUploadPhotoServiceModel userUploadPhotoServiceModel = this.modelMapper.map(userUploadPhotoBindingModel, UserUploadPhotoServiceModel.class);

        this.userService.uploadProfilePicture(username, userUploadPhotoServiceModel);

        return "redirect:/users/profile/" + username;
    }

    @DeleteMapping("/profile/delete-photo/{username}")
    public String deletePhoto(@PathVariable String username) {

        this.userService.deleteProfilePicture(username);

        return "redirect:/users/profile/" + username;
    }

    @GetMapping("/profile/edit-profile/{username}")
    public String editProfile(@PathVariable String username, Model model) {

        UserProfileEditViewModel userProfileEditViewModel = this.userService.getUserProfileEditViewModel(username);
        UserProfileEditBindingModel userProfileEditBindingModel = this.modelMapper.map(userProfileEditViewModel, UserProfileEditBindingModel.class);

        model.addAttribute("userProfileEditBindingModel", userProfileEditBindingModel);

        return "edit-profile";
    }

    @GetMapping("/profile/edit-profile/errors/{username}")
    public String EditProfileErrors(@PathVariable String username) {

        return "edit-profile";
    }

    @PatchMapping("/profile/edit-profile/{username}")
    public String editProfileConfirm(@PathVariable String username,
                                     @Valid UserProfileEditBindingModel userProfileEditBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileEditBindingModel", userProfileEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileEditBindingModel", bindingResult);

            return "redirect:/users/profile/edit-profile/errors/" + username;
        }

        UserProfileEditServiceModel userProfileEditServiceModel = this.modelMapper.map(userProfileEditBindingModel, UserProfileEditServiceModel.class);

        this.userService.editUserProfile(username, userProfileEditServiceModel);

        return "redirect:/users/profile/" + username;
    }
}
