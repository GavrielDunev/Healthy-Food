package com.example.healthyfood.service;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;

import java.util.List;

public interface MealService {

    List<RecipeAllSummaryViewModel> getAllBreakfastViews();

    List<RecipeAllSummaryViewModel> getAllLunchViews();

    List<RecipeAllSummaryViewModel> getAllDinnerViews();
}
