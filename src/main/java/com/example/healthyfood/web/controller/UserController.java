package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.UserRegisterBindingModel;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.UserRegisterServiceModel;
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
import java.security.Principal;

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

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {

        UserEntity currentUser = this.userService.findByUsername(principal.getName());
        UserProfileViewModel userProfileViewModel = this.modelMapper.map(currentUser, UserProfileViewModel.class);

        model.addAttribute("user", userProfileViewModel);

        return "profile";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id) {
        return "profile";
    }

    @GetMapping("/profile/recipes/{username}")
    public String userRecipeList(@PathVariable String username) {

        this.userService.findAllUserRecipes(username);

        return "user-recipes";
    }

}
