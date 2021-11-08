package com.example.healthyfood.model.entity;

import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
public class RecipeEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;
    @OneToOne
    private PictureEntity picture;
    @Column(nullable = false)
    private Integer prepTime;
    @Column(nullable = false)
    private Integer cookTime;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RecipeDifficultyEnum difficulty;
    @ManyToOne
    private UserEntity author;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String instructions;

    public String getTitle() {
        return title;
    }

    public RecipeEntity setTitle(String name) {
        this.title = name;
        return this;
    }

    public PictureEntity getPicture() {
        return picture;
    }

    public RecipeEntity setPicture(PictureEntity picture) {
        this.picture = picture;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public RecipeEntity setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public RecipeEntity setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RecipeEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public RecipeEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public RecipeDifficultyEnum getDifficulty() {
        return difficulty;
    }

    public RecipeEntity setDifficulty(RecipeDifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RecipeEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getIngredients() {
        return ingredients;
    }

    public RecipeEntity setIngredients(String ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public String getInstructions() {
        return instructions;
    }

    public RecipeEntity setInstructions(String instructions) {
        this.instructions = instructions;
        return this;
    }

}
