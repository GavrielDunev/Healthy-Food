package com.example.healthyfood.service;

import com.example.healthyfood.model.view.RecipeSummaryViewModel;

import java.util.List;

public interface RecipeService {
    List<RecipeSummaryViewModel> getAllRecipeViews();
}
