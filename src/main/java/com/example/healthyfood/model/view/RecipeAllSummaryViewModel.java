package com.example.healthyfood.model.view;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeAllSummaryViewModel {

    private Long recipeId;
    private String recipeTitle;
    private String recipeDescription;
    private RecipeDifficultyEnum recipeDifficulty;
    private String recipePictureUrl;

    public Long getRecipeId() {
        return recipeId;
    }

    public RecipeAllSummaryViewModel setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public RecipeAllSummaryViewModel setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
        return this;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public RecipeAllSummaryViewModel setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
        return this;
    }

    public RecipeDifficultyEnum getRecipeDifficulty() {
        return recipeDifficulty;
    }

    public RecipeAllSummaryViewModel setRecipeDifficulty(RecipeDifficultyEnum recipeDifficulty) {
        this.recipeDifficulty = recipeDifficulty;
        return this;
    }

    public String getRecipePictureUrl() {
        return recipePictureUrl;
    }

    public RecipeAllSummaryViewModel setRecipePictureUrl(String recipePictureUrl) {
        this.recipePictureUrl = recipePictureUrl;
        return this;
    }
}
