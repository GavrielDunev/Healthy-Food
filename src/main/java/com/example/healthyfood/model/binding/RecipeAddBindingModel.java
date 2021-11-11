package com.example.healthyfood.model.binding;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.model.validator.ValidPicture;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;

public class RecipeAddBindingModel {

    @NotBlank
    @Size(min = 5)
    private String title;
    @ValidPicture
    private MultipartFile picture;
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
    @NotBlank
    @Size(min = 5)
    private String ingredients;
    @NotBlank
    @Size(min = 5)
    private String instructions;

    public String getTitle() {
        return title;
    }

    public RecipeAddBindingModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public RecipeAddBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeAddBindingModel setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeAddBindingModel setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeAddBindingModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeAddBindingModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeAddBindingModel setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }
}
