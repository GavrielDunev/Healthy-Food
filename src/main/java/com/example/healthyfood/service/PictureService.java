package com.example.healthyfood.service;

import com.example.healthyfood.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

public interface PictureService {

    PictureEntity createPicture(MultipartFile picture) throws IOException;

    PictureEntity findById(Long id);

    void deletePicture(Long id);
}
