package com.example.healthyfood.model.entity;

import com.example.healthyfood.model.entity.enums.MealCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "meals")
public class MealEntity extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private Integer prepTime;
    @Column(nullable = false)
    private Integer cookTime;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MealCategoryEnum category;
    @OneToOne
    private RecipeEntity recipe;

    public String getName() {
        return name;
    }

    public MealEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public MealEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public MealEntity setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
        return this;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public MealEntity setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MealEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public MealEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }

    public MealCategoryEnum getCategory() {
        return category;
    }

    public MealEntity setCategory(MealCategoryEnum category) {
        this.category = category;
        return this;
    }
}
