package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DrinkController {

    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/drinks")
    public String drinks(Model model) {

        List<RecipeAllSummaryViewModel> drinks = this.drinkService.getAllDrinkViews();

        model.addAttribute("type", "Drink");
        model.addAttribute("recipes", drinks);

        return "all-recipes";
    }
}
