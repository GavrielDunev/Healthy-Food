package com.example.healthyfood.service;

import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;

import java.io.IOException;
import java.util.List;

public interface MealService {

    List<RecipeAllSummaryViewModel> getAllBreakfastViews();

    List<RecipeAllSummaryViewModel> getAllLunchViews();

    List<RecipeAllSummaryViewModel> getAllDinnerViews();

    void saveMeal(RecipeAddServiceModel recipeAddServiceModel, String username) throws IOException;
}
