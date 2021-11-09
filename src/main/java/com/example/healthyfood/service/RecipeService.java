package com.example.healthyfood.service;

import com.example.healthyfood.model.view.RecipeHomeSummaryViewModel;

import java.util.List;

public interface RecipeService {
    List<RecipeHomeSummaryViewModel> getLastSixRecipeViews();
}
