package com.example.healthyfood.model.service;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import org.springframework.web.multipart.MultipartFile;

public class RecipeAddServiceModel {

    private String title;
    private MultipartFile picture;
    private Integer prepTime;
    private Integer cookTime;
    private String description;
    private RecipeDifficultyEnum difficulty;
    private String ingredients;
    private String instructions;

    public String getTitle() {
        return title;
    }

    public RecipeAddServiceModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public RecipeAddServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeAddServiceModel setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeAddServiceModel setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeAddServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeAddServiceModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeAddServiceModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeAddServiceModel setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
}
