package com.example.healthyfood.model.view;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeHomeSummaryViewModel {

    private Long id;
    private String title;
    private String pictureUrl;
    private String description;
    private RecipeDifficultyEnum difficulty;

    public String getTitle() {
        return title;
    }

    public RecipeHomeSummaryViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RecipeHomeSummaryViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeHomeSummaryViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RecipeHomeSummaryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeHomeSummaryViewModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}
