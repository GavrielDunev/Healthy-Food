package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.DessertEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.repository.DessertRepository;
import com.example.healthyfood.service.PictureService;
import com.example.healthyfood.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DessertServiceImplTest {

    private DessertServiceImpl testService;

    private DessertEntity dessert1;

    private DessertEntity dessert2;

    @Mock
    private DessertRepository dessertRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {

        this.testService = new DessertServiceImpl(this.dessertRepository, this.pictureService,
                this.userService, this.modelMapper);

        RecipeEntity recipe1 = new RecipeEntity()
                .setTitle("First recipe")
                .setPrepTime(1)
                .setCookTime(1)
                .setDescription("First description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.EASY)
                .setIngredients("First ingredients")
                .setInstructions("First instructions");

        RecipeEntity recipe2 = new RecipeEntity()
                .setTitle("Second recipe")
                .setPrepTime(2)
                .setCookTime(2)
                .setDescription("Second description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.MEDIUM)
                .setIngredients("Second ingredients")
                .setInstructions("Second instructions");

        this.dessert1 = new DessertEntity()
                .setRecipe(recipe1);

        this.dessert2 = new DessertEntity()
                .setRecipe(recipe2);
    }

    @Test
    public void testGetAllDessertViews() {

        when(this.dessertRepository.findAllOrderByCreatedDesc())
                .thenReturn(List.of(dessert1, dessert2));

        List<RecipeSummaryViewModel> testDessertViews = this.testService.getAllDessertViews();

        assertNotNull(testDessertViews);
        assertEquals(testDessertViews.size(), 2);
    }

    @Test
    public void testSaveDessert() throws IOException {

        MultipartFile multipartFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return null;
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IllegalStateException {

            }
        };

        RecipeAddServiceModel testRecipeAddServiceModel = new RecipeAddServiceModel()
                .setTitle("testService")
                .setPicture(multipartFile)
                .setTitle(this.dessert1.getRecipe().getTitle())
                .setDifficulty(this.dessert1.getRecipe().getDifficulty())
                .setDescription(this.dessert1.getRecipe().getDescription())
                .setIngredients(this.dessert1.getRecipe().getIngredients())
                .setInstructions(this.dessert1.getRecipe().getInstructions());

        when(this.modelMapper.map(testRecipeAddServiceModel, RecipeEntity.class))
                .thenReturn(this.dessert1.getRecipe());

        String username = "test";

        this.testService.saveDessert(testRecipeAddServiceModel, username);

        verify(this.pictureService, times(1)).createPicture(testRecipeAddServiceModel.getPicture());
        verify(this.userService, times(1)).findByUsername(username);
    }
}