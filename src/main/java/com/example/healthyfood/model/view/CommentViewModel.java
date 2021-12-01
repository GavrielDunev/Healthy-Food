package com.example.healthyfood.model.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommentViewModel {

    private Long id;
    private String message;
    private String author;
    private String authorProfilePictureUrl;
    private String created;
    private boolean canDelete;

    public Long getId() {
        return id;
    }

    public CommentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public CommentViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getCreated() {
        return created;
    }

    public CommentViewModel setCreated(LocalDateTime created) {
        this.created = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy").format(created);
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }

    public String getAuthorProfilePictureUrl() {
        return authorProfilePictureUrl;
    }

    public CommentViewModel setAuthorProfilePictureUrl(String authorProfilePictureUrl) {
        this.authorProfilePictureUrl = authorProfilePictureUrl;
        return this;
    }
}
