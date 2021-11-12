package com.example.healthyfood.model.binding;

import com.example.healthyfood.model.entity.enums.MealCategoryEnum;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

import javax.validation.constraints.*;

public class RecipeEditBindingModel {

    private Long id;
    @NotBlank
    @Size(min = 5)
    private String title;
    @NotNull
    @Positive
    private Integer prepTime;
    @NotNull
    @PositiveOrZero
    private Integer cookTime;
    @NotBlank
    @Size(min = 5)
    private String description;
    @NotNull
    private RecipeDifficultyEnum difficulty;
    private MealCategoryEnum category;
    @NotBlank
    @Size(min = 5)
    private String ingredients;
    @NotBlank
    @Size(min = 5)
    private String instructions;
    private boolean isMeal;

    public Long getId() {
        return id;
    }

    public RecipeEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeEditBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeEditBindingModel setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeEditBindingModel setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEditBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeEditBindingModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public MealCategoryEnum getCategory() {
        return category;
    }

    public RecipeEditBindingModel setCategory(MealCategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeEditBindingModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeEditBindingModel setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public boolean isMeal() {
        return isMeal;
    }

    public RecipeEditBindingModel setIsMeal(boolean meal) {
        isMeal = meal;
        return this;
    }
}
