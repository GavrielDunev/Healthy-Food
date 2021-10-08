package com.example.healthyfood.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity{

    @ManyToOne
    private UserEntity author;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

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
