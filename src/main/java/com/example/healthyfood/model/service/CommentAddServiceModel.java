package com.example.healthyfood.model.service;

public class CommentAddServiceModel {

    private Long recipeId;
    private String message;
    private String author;

    public Long getRecipeId() {
        return recipeId;
    }

    public CommentAddServiceModel setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentAddServiceModel setMessage(String message) {
        this.message = message;
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
