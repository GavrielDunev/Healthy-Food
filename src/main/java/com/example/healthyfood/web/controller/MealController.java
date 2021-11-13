package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.RecipeAddBindingModel;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.service.MealService;
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
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;
    private final ModelMapper modelMapper;

    public MealController(MealService mealService, ModelMapper modelMapper) {
        this.mealService = mealService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/breakfast")
    public String breakfast(Model model) {

        List<RecipeSummaryViewModel> breakfastRecipes = this.mealService.getAllBreakfastViews();

        model.addAttribute("type", "Breakfast");
        model.addAttribute("recipes", breakfastRecipes);

        return "recipes";
    }

    @GetMapping("/lunch")
    public String lunch(Model model) {

        List<RecipeSummaryViewModel> lunchRecipes = this.mealService.getAllLunchViews();

        model.addAttribute("type", "Lunch");
        model.addAttribute("recipes", lunchRecipes);

        return "recipes";
    }

    @GetMapping("/dinner")
    public String dinner(Model model) {

        List<RecipeSummaryViewModel> dinnerRecipes = this.mealService.getAllDinnerViews();

        model.addAttribute("type", "Dinner");
        model.addAttribute("recipes", dinnerRecipes);

        return "recipes";
    }

    @GetMapping("/add")
    public String mealAdd(Model model) {

        model.addAttribute("type", "meal");

        return "add-recipe";
    }

    @PostMapping("/add")
    public String mealAddConfirm(@Valid RecipeAddBindingModel recipeAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Principal principal) throws IOException {

        if (bindingResult.hasErrors() || recipeAddBindingModel.getCategory() == null) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeAddBindingModel", bindingResult);

            if (recipeAddBindingModel.getCategory() == null) {
                redirectAttributes.addFlashAttribute("invalidCategory", true);
            }

            return "redirect:/meals/add";
        }

        RecipeAddServiceModel recipeAddServiceModel = this.modelMapper.map(recipeAddBindingModel, RecipeAddServiceModel.class);

        this.mealService.saveMeal(recipeAddServiceModel, principal.getName());

        return "redirect:/meals/" + recipeAddServiceModel.getCategory().name().toLowerCase();
    }

    @ModelAttribute
    public RecipeAddBindingModel recipeAddBindingModel() {
        return new RecipeAddBindingModel();
    }
}
