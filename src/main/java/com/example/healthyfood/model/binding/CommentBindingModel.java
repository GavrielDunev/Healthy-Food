package com.example.healthyfood.model.binding;

import javax.validation.constraints.NotBlank;

public class CommentBindingModel {

    @NotBlank
    private String text;

    public String getText() {
        return text;
    }

    public CommentBindingModel setText(String text) {
        this.text = text;
        return this;
    }
}
