package com.example.healthyfood.model.entity;

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
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @OneToOne
    private RecipeEntity recipe;

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

    public String getDescription() {
        return description;
    }

    public DrinkEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public DrinkEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }
}
