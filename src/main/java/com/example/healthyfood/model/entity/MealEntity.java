package com.example.healthyfood.model.entity;

import com.example.healthyfood.model.entity.enums.MealCategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "meals")
public class MealEntity extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MealCategoryEnum category;
    @OneToOne
    private RecipeEntity recipe;

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
