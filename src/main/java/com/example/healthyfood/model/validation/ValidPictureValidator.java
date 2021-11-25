package com.example.healthyfood.model.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidPictureValidator implements ConstraintValidator<ValidPicture, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        String contentType = multipartFile.getContentType();

        return isSupportedContentType(contentType);
    }

    private boolean isSupportedContentType(String contentType) {
        List<String> supportedContents = List.of("image/jpg", "image/jpeg", "image/png");
        return supportedContents.contains(contentType);
    }
}