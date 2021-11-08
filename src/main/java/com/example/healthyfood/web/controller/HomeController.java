package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<RecipeSummaryViewModel> recipes = this.recipeService.getAllRecipeViews();

        model.addAttribute("recipes", recipes);

        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
