package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.service.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/meals")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/breakfast")
    public String breakfast(Model model) {

        List<RecipeAllSummaryViewModel> breakfastRecipes = this.mealService.getAllBreakfastViews();

        model.addAttribute("type", "Breakfast");
        model.addAttribute("recipes", breakfastRecipes);

        return "all-recipes";
    }

    @GetMapping("/lunch")
    public String lunch(Model model) {

        List<RecipeAllSummaryViewModel> lunchRecipes = this.mealService.getAllLunchViews();

        model.addAttribute("type", "Lunch");
        model.addAttribute("recipes", lunchRecipes);

        return "all-recipes";
    }

    @GetMapping("dinner")
    public String dinner(Model model) {

        List<RecipeAllSummaryViewModel> dinnerRecipes = this.mealService.getAllDinnerViews();

        model.addAttribute("type", "Dinner");
        model.addAttribute("recipes", dinnerRecipes);

        return "all-recipes";
    }
}
