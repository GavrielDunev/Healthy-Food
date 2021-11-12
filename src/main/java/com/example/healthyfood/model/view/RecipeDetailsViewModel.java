package com.example.healthyfood.model.view;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeDetailsViewModel {

    private Long id;
    private String title;
    private String pictureUrl;
    private Integer prepTime;
    private Integer cookTime;
    private String description;
    private RecipeDifficultyEnum difficulty;
    private String ingredients;
    private String instructions;
    private String authorProfilePictureUrl;
    private Long authorId;
    private String authorUsername;

    public Long getId() {
        return id;
    }

    public RecipeDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public RecipeDetailsViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RecipeDetailsViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeDetailsViewModel setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeDetailsViewModel setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeDetailsViewModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeDetailsViewModel setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeDetailsViewModel setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

    public String getAuthorProfilePictureUrl() {
        return authorProfilePictureUrl;
    }

    public RecipeDetailsViewModel setAuthorProfilePictureUrl(String authorProfilePictureUrl) {
        this.authorProfilePictureUrl = authorProfilePictureUrl;
        return this;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public RecipeDetailsViewModel setAuthorId(Long authorId) {
        this.authorId = authorId;
        return this;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public RecipeDetailsViewModel setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
        return this;
    }

}
