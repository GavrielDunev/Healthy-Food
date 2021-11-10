package com.example.healthyfood.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "drinks")
public class DrinkEntity extends BaseEntity{

    @OneToOne(cascade = CascadeType.PERSIST)
    private RecipeEntity recipe;

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public DrinkEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }
}
