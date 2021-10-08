package com.example.healthyfood.model.entity;

import com.example.healthyfood.model.entity.enums.DifficultyEnum;

import javax.persistence.*;

@Entity
@Table(name = "foods")
public class FoodEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Integer prepTime;
    @Column(nullable = false)
    private Integer cookTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DifficultyEnum difficulty;
    @OneToOne
    private RecipeEntity recipe;
    @ManyToOne
    private UserEntity author;

    public String getName() {
        return name;
    }

    public FoodEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public FoodEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public FoodEntity setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public FoodEntity setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public FoodEntity setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public FoodEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public FoodEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
