package com.example.healthyfood.model.service;

import org.springframework.web.multipart.MultipartFile;

public class UserUploadPhotoServiceModel {

    private MultipartFile picture;

    public MultipartFile getPicture() {
        return picture;
    }

    public UserUploadPhotoServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
