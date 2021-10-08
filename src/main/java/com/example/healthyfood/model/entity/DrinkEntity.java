package com.example.healthyfood.model.entity;

import com.example.healthyfood.model.entity.enums.DifficultyEnum;

import javax.persistence.*;

@Entity
@Table(name = "drinks")
public class DrinkEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Integer prepTime;
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

    public DrinkEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DrinkEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public DrinkEntity setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public DifficultyEnum getDifficulty() {
        return difficulty;
    }

    public DrinkEntity setDifficulty(DifficultyEnum difficulty) {
        this.difficulty = difficulty;
        return this;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public DrinkEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public DrinkEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
