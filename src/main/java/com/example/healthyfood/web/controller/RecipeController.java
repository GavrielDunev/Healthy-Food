package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.view.RecipeDetailsViewModel;
import com.example.healthyfood.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/details/{id}")
    public String recipeDetails(@PathVariable Long id, Model model) {

        RecipeDetailsViewModel recipeDetailsView = this.recipeService.getRecipeDetailsViewById(id);

        model.addAttribute("recipe", recipeDetailsView);

        return "details";
    }

}