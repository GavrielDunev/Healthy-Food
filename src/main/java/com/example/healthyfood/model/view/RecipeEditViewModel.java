package com.example.healthyfood.model.view;

import com.example.healthyfood.model.entity.enums.MealCategoryEnum;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeEditViewModel {

    private Long id;
    private String title;
    private Integer prepTime;
    private Integer cookTime;
    private String description;
    private RecipeDifficultyEnum difficulty;
    private MealCategoryEnum mealCategory;
    private String ingredients;
    private String instructions;
    private boolean isMeal;

    public Long getId() {
        return id;
    }

    public RecipeEditViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeEditViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeEditViewModel setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeEditViewModel setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEditViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeEditViewModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeEditViewModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeEditViewModel setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }


    public MealCategoryEnum getMealCategory() {
        return mealCategory;
    }

    public RecipeEditViewModel setMealCategory(MealCategoryEnum mealCategory) {
        this.mealCategory = mealCategory;
        return this;
    }

    public boolean IsMeal() {
        return isMeal;
    }

    public RecipeEditViewModel setIsMeal(boolean meal) {
        isMeal = meal;
        return this;
    }
}
