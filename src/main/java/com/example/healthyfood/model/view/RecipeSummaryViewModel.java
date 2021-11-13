package com.example.healthyfood.model.view;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeSummaryViewModel {

    private Long recipeId;
    private String recipeTitle;
    private String recipeDescription;
    private RecipeDifficultyEnum recipeDifficulty;
    private String recipePictureUrl;

    public Long getRecipeId() {
        return recipeId;
    }

    public RecipeSummaryViewModel setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public RecipeSummaryViewModel setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
        return this;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public RecipeSummaryViewModel setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
        return this;
    }

    public RecipeDifficultyEnum getRecipeDifficulty() {
        return recipeDifficulty;
    }

    public RecipeSummaryViewModel setRecipeDifficulty(RecipeDifficultyEnum recipeDifficulty) {
        this.recipeDifficulty = recipeDifficulty;
        return this;
    }

    public String getRecipePictureUrl() {
        return recipePictureUrl;
    }

    public RecipeSummaryViewModel setRecipePictureUrl(String recipePictureUrl) {
        this.recipePictureUrl = recipePictureUrl;
        return this;
    }
}
