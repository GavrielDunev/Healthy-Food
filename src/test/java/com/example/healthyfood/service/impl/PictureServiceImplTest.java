package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.repository.PictureRepository;
import com.example.healthyfood.service.CloudinaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PictureServiceImplTest {

    private PictureServiceImpl testService;

    private PictureEntity testPicture;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private CloudinaryService cloudinaryService;

    @Mock
    private MultipartFile multipartFile;

    @BeforeEach
    public void setUp() {

        this.testService = new PictureServiceImpl(this.pictureRepository, this.cloudinaryService);

        this.testPicture = new PictureEntity().setUrl("testUrl").setPublicId("24215215");
    }

    @Test
    public void testCreatePicture() throws IOException {

        when(this.pictureRepository.save(this.testPicture))
                .thenReturn(this.testPicture);

        when(this.cloudinaryService.upload(this.multipartFile))
                .thenReturn(this.testPicture);

        PictureEntity result = this.testService.createPicture(this.multipartFile);

        assertEquals(this.testPicture.getPublicId(), result.getPublicId());
        assertEquals(this.testPicture.getUrl(), result.getUrl());
    }

    @Test
    public void testDeletePicture() {

        int testId = 1;

        this.testService.deletePicture((long) testId);

        verify(this.pictureRepository, times(1)).deleteById((long) testId);
    }
}