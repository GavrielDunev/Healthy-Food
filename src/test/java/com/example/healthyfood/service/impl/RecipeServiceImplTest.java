package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.*;
import com.example.healthyfood.model.entity.enums.MealCategoryEnum;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.model.service.RecipeEditServiceModel;
import com.example.healthyfood.model.view.RecipeDetailsViewModel;
import com.example.healthyfood.model.view.RecipeEditViewModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.repository.RecipeRepository;
import com.example.healthyfood.service.CloudinaryService;
import com.example.healthyfood.service.UserService;
import com.example.healthyfood.service.scheduler.CacheCleaner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    private RecipeServiceImpl testService;

    private RecipeEntity recipe1;

    private RecipeEntity recipe2;

    private UserEntity testUser;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CloudinaryService cloudinaryService;

    @Mock
    private UserService userService;

    @Mock
    private CacheCleaner cacheCleaner;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {

        this.testService = new RecipeServiceImpl(this.recipeRepository, this.cloudinaryService,
                this.userService, this.cacheCleaner, this.modelMapper);

        MealEntity testMeal = new MealEntity()
                .setCategory(MealCategoryEnum.DINNER);

        PictureEntity testPicture = new PictureEntity()
                .setPublicId("24151512")
                .setUrl("dadad");

        this.recipe1 = new RecipeEntity()
                .setTitle("First recipe")
                .setPrepTime(1)
                .setCookTime(1)
                .setDescription("First description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.EASY)
                .setIngredients("First ingredients")
                .setInstructions("First instructions")
                .setMeal(testMeal)
                .setPicture(testPicture);

        this.recipe2 = new RecipeEntity()
                .setTitle("Second recipe")
                .setPrepTime(2)
                .setCookTime(2)
                .setDescription("Second description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.MEDIUM)
                .setIngredients("Second ingredients")
                .setInstructions("Second instructions");

        UserRoleEntity user = new UserRoleEntity()
                .setRole(UserRoleEnum.USER);
        UserRoleEntity admin = new UserRoleEntity()
                .setRole(UserRoleEnum.ADMIN);

        this.testUser = new UserEntity()
                .setUsername("test")
                .setPassword("213451521")
                .setEmail("email")
                .setFirstName("first name")
                .setLastName("last name")
                .setRecipes(Set.of(this.recipe1, this.recipe2))
                .setRoles(Set.of(user, admin));
    }

    @Test
    public void testGetLastSixRecipeViews() {

        when(this.recipeRepository.findRecipesOrderByCreatedDesc())
                .thenReturn(List.of(this.recipe1, this.recipe2));

        List<RecipeSummaryViewModel> result = this.testService.getLastSixRecipeViews();

        assertEquals(result.size(), 2);
        verify(this.recipeRepository, times(1)).findRecipesOrderByCreatedDesc();
    }

    @Test
    public void testGetRecipeDetailsViewById() {

        RecipeDetailsViewModel mappedRecipe = new RecipeDetailsViewModel()
                .setTitle(this.recipe1.getTitle())
                .setDifficulty(this.recipe1.getDifficulty())
                .setDescription(this.recipe1.getDescription())
                .setInstructions(this.recipe1.getInstructions())
                .setIngredients(this.recipe1.getIngredients())
                .setCookTime(this.recipe1.getCookTime())
                .setPrepTime(this.recipe1.getPrepTime());

        int testId = 1;

        when(this.modelMapper.map(this.recipe1, RecipeDetailsViewModel.class))
                .thenReturn(mappedRecipe);

        when(this.recipeRepository.findById((long) testId))
                .thenReturn(Optional.of(this.recipe1));

        when(this.userService.findByUsername(this.testUser.getUsername()))
                .thenReturn(this.testUser);


        RecipeDetailsViewModel result = this.testService.getRecipeDetailsViewById((long) testId, this.testUser.getUsername());

        assertNotNull(result);
        assertEquals(result.getTitle(), mappedRecipe.getTitle());
        assertEquals(result.getCookTime(), mappedRecipe.getCookTime());
        assertEquals(result.getPrepTime(), mappedRecipe.getPrepTime());
        assertEquals(result.getInstructions(), mappedRecipe.getInstructions());
    }

    @Test
    public void testGetRecipeEditViewById() {

        int testId = 1;

        RecipeEditViewModel mappedRecipe = new RecipeEditViewModel()
                .setTitle(this.recipe1.getTitle())
                .setDifficulty(this.recipe1.getDifficulty())
                .setDescription(this.recipe1.getDescription())
                .setInstructions(this.recipe1.getInstructions())
                .setIngredients(this.recipe1.getIngredients())
                .setCookTime(this.recipe1.getCookTime())
                .setPrepTime(this.recipe1.getPrepTime());

        when(this.modelMapper.map(this.recipe1, RecipeEditViewModel.class))
                .thenReturn(mappedRecipe);

        when(this.recipeRepository.findById((long) testId))
                .thenReturn(Optional.of(this.recipe1));

        RecipeEditViewModel result = this.testService.getRecipeEditViewById((long) testId);

        assertNotNull(result);
        assertEquals(result.getTitle(), mappedRecipe.getTitle());
        assertEquals(result.getCookTime(), mappedRecipe.getCookTime());
        assertEquals(result.getPrepTime(), mappedRecipe.getPrepTime());
        assertEquals(result.getInstructions(), mappedRecipe.getInstructions());
        assertTrue(result.getIsMeal());
    }

    @Test
    public void testEditRecipe() {

        int testId = 1;

        when(this.recipeRepository.findById((long) testId))
                .thenReturn(Optional.of(this.recipe1));


        RecipeEditServiceModel testRecipe = new RecipeEditServiceModel()
                .setId((long) testId)
                .setTitle(this.recipe1.getTitle())
                .setDescription(this.recipe1.getDescription())
                .setDifficulty(this.recipe1.getDifficulty())
                .setCategory(MealCategoryEnum.BREAKFAST);

        this.testService.editRecipe(testRecipe);

        verify(this.recipeRepository, times(1)).save(this.recipe1);
    }

    @Test
    public void testDeleteRecipe() {

        int testId = 1;

        when(this.recipeRepository.findById((long) testId))
                .thenReturn(Optional.of(this.recipe1));

        when(this.cloudinaryService.delete(this.recipe1.getPicture().getPublicId()))
                .thenReturn(true);

        this.testService.deleteRecipe((long) testId);

        verify(this.cloudinaryService, times(1)).delete(this.recipe1.getPicture().getPublicId());
        verify(this.recipeRepository, times(1)).delete(this.recipe1);
        verify(this.cacheCleaner, times(1)).removeCachedRecipes();
    }
}