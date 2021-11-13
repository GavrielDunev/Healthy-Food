package com.example.healthyfood.service;

import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;

import java.io.IOException;
import java.util.List;

public interface DessertService {

    List<RecipeSummaryViewModel> getAllDessertViews();

    void saveDessert(RecipeAddServiceModel recipeAddServiceModel, String username) throws IOException;
}
