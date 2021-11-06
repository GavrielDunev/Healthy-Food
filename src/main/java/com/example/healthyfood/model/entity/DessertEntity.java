package com.example.healthyfood.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "desserts")
public class DessertEntity extends BaseEntity{

    @OneToOne
    RecipeEntity recipe;

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public DessertEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }
}
