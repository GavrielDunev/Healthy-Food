package com.example.healthyfood.model.service;

import com.example.healthyfood.model.entity.enums.MealCategoryEnum;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeEditServiceModel {

    private Long id;
    private String title;
    private Integer prepTime;
    private Integer cookTime;
    private String description;
    private RecipeDifficultyEnum difficulty;
    private MealCategoryEnum category;
    private String ingredients;
    private String instructions;

    public Long getId() {
        return id;
    }

    public RecipeEditServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeEditServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeEditServiceModel setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeEditServiceModel setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEditServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeEditServiceModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public MealCategoryEnum getCategory() {
        return category;
    }

    public RecipeEditServiceModel setCategory(MealCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeEditServiceModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeEditServiceModel setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
}
