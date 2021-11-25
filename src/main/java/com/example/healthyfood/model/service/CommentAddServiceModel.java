package com.example.healthyfood.model.service;

public class CommentAddServiceModel {

    private Long recipeId;
    private String text;
    private String author;

    public Long getRecipeId() {
        return recipeId;
    }

    public CommentAddServiceModel setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentAddServiceModel setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentAddServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

}
