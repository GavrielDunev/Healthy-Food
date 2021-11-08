package com.example.healthyfood.model.view;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

public class RecipeSummaryViewModel {

    private Long id;
    private String title;
    private String pictureUrl;
    private String description;
    private RecipeDifficultyEnum difficulty;

    public String getTitle() {
        return title;
    }

    public RecipeSummaryViewModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public RecipeSummaryViewModel setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeSummaryViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public RecipeSummaryViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeSummaryViewModel setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }
}
