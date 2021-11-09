package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.service.DessertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DessertController {

    private final DessertService dessertService;

    public DessertController(DessertService dessertService) {
        this.dessertService = dessertService;
    }

    @GetMapping("/desserts")
    public String desserts(Model model) {

        List<RecipeAllSummaryViewModel> desserts = this.dessertService.getAllDessertViews();

        model.addAttribute("type", "Dessert");
        model.addAttribute("recipes", desserts);

        return "all-recipes";
    }
}
