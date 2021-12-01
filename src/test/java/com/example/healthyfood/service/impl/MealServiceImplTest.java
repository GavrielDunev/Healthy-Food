package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.DrinkEntity;
import com.example.healthyfood.model.entity.MealEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.enums.MealCategoryEnum;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.repository.DrinkRepository;
import com.example.healthyfood.repository.MealRepository;
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
class MealServiceImplTest {

    private MealServiceImpl testService;

    private MealEntity meal1;

    private MealEntity meal2;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private UserService userService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void init() {

        this.testService = new MealServiceImpl(this.mealRepository, this.pictureService,
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

        this.meal1 = new MealEntity()
                .setRecipe(recipe1);

        this.meal2 = new MealEntity()
                .setRecipe(recipe2);
    }

    @Test
    public void testGetAllBreakfastViews() {

        when(this.mealRepository.findAllBreakfastOrderByCreatedDesc())
                .thenReturn(List.of(meal1, meal2));

        List<RecipeSummaryViewModel> testDrinkViews = this.testService.getAllBreakfastViews();

        assertNotNull(testDrinkViews);
        assertEquals(testDrinkViews.size(), 2);
    }

    @Test
    public void testGetAllLunchViews() {

        when(this.mealRepository.findAllLunchOrderByCreatedDesc())
                .thenReturn(List.of(meal1, meal2));

        List<RecipeSummaryViewModel> testDrinkViews = this.testService.getAllLunchViews();

        assertNotNull(testDrinkViews);
        assertEquals(testDrinkViews.size(), 2);
    }

    @Test
    public void testGetAllDinnerViews() {

        when(this.mealRepository.findAllDinnerOrderByCreatedDesc())
                .thenReturn(List.of(meal1, meal2));

        List<RecipeSummaryViewModel> testDrinkViews = this.testService.getAllDinnerViews();

        assertNotNull(testDrinkViews);
        assertEquals(testDrinkViews.size(), 2);
    }

    @Test
    public void testSaveMeal() throws IOException {

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
                .setTitle(this.meal1.getRecipe().getTitle())
                .setDifficulty(this.meal1.getRecipe().getDifficulty())
                .setDescription(this.meal1.getRecipe().getDescription())
                .setIngredients(this.meal1.getRecipe().getIngredients())
                .setInstructions(this.meal1.getRecipe().getInstructions());

        when(this.modelMapper.map(testRecipeAddServiceModel, RecipeEntity.class))
                .thenReturn(this.meal1.getRecipe());

        String username = "test";

        this.testService.saveMeal(testRecipeAddServiceModel, username);

        verify(this.pictureService, times(1)).createPicture(testRecipeAddServiceModel.getPicture());
        verify(this.userService, times(1)).findByUsername(username);
    }
}