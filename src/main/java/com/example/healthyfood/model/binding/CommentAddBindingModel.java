package com.example.healthyfood.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentAddBindingModel {

    @NotBlank
    @Size(min = 5)
    private String text;

    public String getText() {
        return text;
    }

    public CommentAddBindingModel setText(String text) {
        this.text = text;
        return this;
    }
}
