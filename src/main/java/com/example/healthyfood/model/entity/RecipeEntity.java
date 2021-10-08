package com.example.healthyfood.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "recipies")
public class RecipeEntity extends BaseEntity{

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String instructions;

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
