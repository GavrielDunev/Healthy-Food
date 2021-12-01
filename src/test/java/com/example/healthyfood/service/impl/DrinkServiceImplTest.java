package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.DrinkEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.repository.DrinkRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrinkServiceImplTest {

    private DrinkServiceImpl testService;

    private DrinkEntity drink1;

    private DrinkEntity drink2;

    @Mock
    private DrinkRepository drinkRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {

        this.testService = new DrinkServiceImpl(this.drinkRepository, this.pictureService,
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

        this.drink1 = new DrinkEntity()
                .setRecipe(recipe1);

        this.drink2 = new DrinkEntity()
                .setRecipe(recipe2);
    }

    @Test
    public void testGetAllDrinkViews() {

        when(this.drinkRepository.findAllOrderByCreatedDesc())
                .thenReturn(List.of(drink1, drink2));

        List<RecipeSummaryViewModel> testDrinkViews = this.testService.getAllDrinkViews();

        assertNotNull(testDrinkViews);
        assertEquals(testDrinkViews.size(), 2);
    }

    @Test
    public void testSaveDrink() throws IOException {

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
                .setTitle(this.drink1.getRecipe().getTitle())
                .setDifficulty(this.drink1.getRecipe().getDifficulty())
                .setDescription(this.drink1.getRecipe().getDescription())
                .setIngredients(this.drink1.getRecipe().getIngredients())
                .setInstructions(this.drink1.getRecipe().getInstructions());

        when(this.modelMapper.map(testRecipeAddServiceModel, RecipeEntity.class))
                .thenReturn(this.drink1.getRecipe());

        String username = "test";

        this.testService.saveDrink(testRecipeAddServiceModel, username);

        verify(this.pictureService, times(1)).createPicture(testRecipeAddServiceModel.getPicture());
        verify(this.userService, times(1)).findByUsername(username);
    }
}