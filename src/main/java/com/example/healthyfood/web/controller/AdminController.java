package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.AdminAddRoleBindingModel;
import com.example.healthyfood.model.service.AdminAddRoleServiceModel;
import com.example.healthyfood.model.view.AdminAddRoleViewModel;
import com.example.healthyfood.service.StatisticsService;
import com.example.healthyfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final StatisticsService statisticsService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService, StatisticsService statisticsService, ModelMapper modelMapper) {
        this.userService = userService;
        this.statisticsService = statisticsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add-role")
    public String addRole(Model model, Principal principal) {

        List<AdminAddRoleViewModel> usernames = this.userService.getAllUserUsernamesWithoutCurrent(principal.getName());

        model.addAttribute("usernames", usernames);

        return "add-role";
    }

    @PatchMapping("/add-role")
    public String AddRoleConfirm(@Valid AdminAddRoleBindingModel adminAddRoleBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("adminAddRoleBindingModel", adminAddRoleBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.adminAddRoleBindingModel", bindingResult);

            return "redirect:/admin/add-role";
        }

        AdminAddRoleServiceModel addRoleServiceModel = this.modelMapper.map(adminAddRoleBindingModel, AdminAddRoleServiceModel.class);

        this.userService.addRole(addRoleServiceModel);

        return "redirect:/";
    }

    @ModelAttribute
    public AdminAddRoleBindingModel adminAddRoleBindingModel() {
        return new AdminAddRoleBindingModel();
    }

    @GetMapping("/statistics")
    public ModelAndView statistics() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("statistics", this.statisticsService.getStatistics());
        modelAndView.setViewName("statistics");

        return modelAndView;
    }
}
