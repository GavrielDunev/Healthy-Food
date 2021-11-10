package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.RecipeAddBindingModel;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.service.DrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/drinks")
public class DrinkController {

    private final DrinkService drinkService;
    private final ModelMapper modelMapper;

    public DrinkController(DrinkService drinkService, ModelMapper modelMapper) {
        this.drinkService = drinkService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String drinks(Model model) {

        List<RecipeAllSummaryViewModel> drinks = this.drinkService.getAllDrinkViews();

        model.addAttribute("type", "Drink");
        model.addAttribute("recipes", drinks);

        return "all-recipes";
    }

    @GetMapping("/add")
    public String drinkAdd(Model model) {

        model.addAttribute("type", "drink");

        return "add-recipe";
    }

    @PostMapping("/add")
    public String drinkAddConfirm(@Valid RecipeAddBindingModel recipeAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeAddBindingModel", bindingResult);

            return "redirect:/drinks/add";
        }

        RecipeAddServiceModel recipeAddServiceModel = this.modelMapper.map(recipeAddBindingModel, RecipeAddServiceModel.class);

        this.drinkService.saveDrink(recipeAddServiceModel, principal.getName());

        return "redirect:/drinks";
    }

    @ModelAttribute
    public RecipeAddBindingModel recipeAddBindingModel() {
        return new RecipeAddBindingModel();
    }
}
