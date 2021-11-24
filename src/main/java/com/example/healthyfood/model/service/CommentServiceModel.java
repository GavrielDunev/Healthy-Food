package com.example.healthyfood.model.service;

public class CommentServiceModel {

    private Long recipeId;
    private String text;
    private String author;

    public Long getRecipeId() {
        return recipeId;
    }

    public CommentServiceModel setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getText() {
        return text;
    }

    public CommentServiceModel setText(String text) {
        this.text = text;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentServiceModel setAuthor(String author) {
        this.author = author;
        return this;
    }

}
