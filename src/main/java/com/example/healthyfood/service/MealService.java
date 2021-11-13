package com.example.healthyfood.service;

import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;

import java.io.IOException;
import java.util.List;

public interface MealService {

    List<RecipeSummaryViewModel> getAllBreakfastViews();

    List<RecipeSummaryViewModel> getAllLunchViews();

    List<RecipeSummaryViewModel> getAllDinnerViews();

    void saveMeal(RecipeAddServiceModel recipeAddServiceModel, String username) throws IOException;
}
