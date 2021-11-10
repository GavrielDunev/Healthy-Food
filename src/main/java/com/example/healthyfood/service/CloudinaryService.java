package com.example.healthyfood.service;

import com.example.healthyfood.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    PictureEntity upload(MultipartFile multipartFile) throws IOException;

    boolean delete(String publicId);
}
