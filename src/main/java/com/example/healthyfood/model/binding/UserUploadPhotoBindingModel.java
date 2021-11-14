package com.example.healthyfood.model.binding;

import com.example.healthyfood.model.validator.ValidPicture;
import org.springframework.web.multipart.MultipartFile;

public class UserUploadPhotoBindingModel {

    @ValidPicture
    private MultipartFile picture;

    public MultipartFile getPicture() {
        return picture;
    }

    public UserUploadPhotoBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
