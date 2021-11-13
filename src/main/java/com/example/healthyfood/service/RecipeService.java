package com.example.healthyfood.service;

import com.example.healthyfood.model.service.RecipeEditServiceModel;
import com.example.healthyfood.model.view.RecipeDetailsViewModel;
import com.example.healthyfood.model.view.RecipeEditViewModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;

import java.util.List;

public interface RecipeService {
    List<RecipeSummaryViewModel> getLastSixRecipeViews();

    RecipeDetailsViewModel getRecipeDetailsViewById(Long id, String username);

    RecipeEditViewModel getRecipeEditViewById(Long id);

    void editRecipe(RecipeEditServiceModel recipeEditServiceModel);

    void deleteRecipe(Long id);

    boolean isOwner(Long id, String username);
}
