package com.example.healthyfood.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @ManyToOne
    private UserEntity author;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
    @Column(nullable = false)
    private LocalDateTime created;
    @ManyToOne
    private RecipeEntity recipe;

    public LocalDateTime getCreated() {
        return created;
    }

    public CommentEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public CommentEntity setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentEntity setMessage(String message) {
        this.message = message;
        return this;
    }
}
