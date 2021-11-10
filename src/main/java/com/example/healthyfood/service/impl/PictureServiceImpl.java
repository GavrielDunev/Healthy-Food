package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.repository.PictureRepository;
import com.example.healthyfood.service.CloudinaryService;
import com.example.healthyfood.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(PictureRepository pictureRepository, CloudinaryService cloudinaryService) {
        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public PictureEntity createPicture(MultipartFile picture) throws IOException {

        PictureEntity newPicture = this.cloudinaryService.upload(picture);

        return this.pictureRepository.save(newPicture);
    }
}
