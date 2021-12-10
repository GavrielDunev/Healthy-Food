package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.UserRoleEntity;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.model.service.RecipeEditServiceModel;
import com.example.healthyfood.model.view.RecipeDetailsViewModel;
import com.example.healthyfood.model.view.RecipeEditViewModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.repository.RecipeRepository;
import com.example.healthyfood.service.CloudinaryService;
import com.example.healthyfood.service.RecipeService;
import com.example.healthyfood.service.UserService;
import com.example.healthyfood.service.exception.ObjectNotFoundException;
import com.example.healthyfood.service.scheduler.CacheCleaner;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;
    private final CacheCleaner cacheCleaner;
    private final ModelMapper modelMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CloudinaryService cloudinaryService, UserService userService, CacheCleaner cacheCleaner, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
        this.cacheCleaner = cacheCleaner;
        this.modelMapper = modelMapper;
    }

    @Cacheable("latestRecipes")
    @Override
    public List<RecipeSummaryViewModel> getLastSixRecipeViews() {

        return this.recipeRepository.findRecipesOrderByCreatedDesc()
                .stream()
                .limit(6)
                .map(recipeEntity -> this.modelMapper.map(recipeEntity, RecipeSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDetailsViewModel getRecipeDetailsViewById(Long id, String username) {

        RecipeEntity recipeEntity = findById(id);

        RecipeDetailsViewModel recipeDetailsViewModel = this.modelMapper.map(recipeEntity, RecipeDetailsViewModel.class);
        recipeDetailsViewModel.setIsOwner(isOwner(id, username));

        return recipeDetailsViewModel;
    }

    @Override
    public RecipeEditViewModel getRecipeEditViewById(Long id) {

        RecipeEntity recipe = findById(id);

        RecipeEditViewModel recipeEditViewModel = this.modelMapper.map(recipe, RecipeEditViewModel.class);

        try {
            recipeEditViewModel.setIsMeal(recipe.getMeal() != null);
        } catch (NullPointerException exception) {
            recipeEditViewModel.setIsMeal(false);
        }

        return recipeEditViewModel;
    }

    @Override
    public void editRecipe(RecipeEditServiceModel recipeEditServiceModel) {

        RecipeEntity recipeEntity = findById(recipeEditServiceModel.getId());

        recipeEntity.setDescription(recipeEditServiceModel.getDescription())
                .setDifficulty(recipeEditServiceModel.getDifficulty())
                .setCookTime(recipeEditServiceModel.getCookTime())
                .setPrepTime(recipeEditServiceModel.getPrepTime())
                .setIngredients(recipeEditServiceModel.getIngredients())
                .setInstructions(recipeEditServiceModel.getInstructions())
                .setTitle(recipeEditServiceModel.getTitle());

        if (recipeEditServiceModel.getCategory() != null) {
            recipeEntity.getMeal().setCategory(recipeEditServiceModel.getCategory());
        }

        this.recipeRepository.save(recipeEntity);
    }

    @Override
    public void deleteRecipe(Long id) {

        RecipeEntity recipeEntity = findById(id);

        if (this.cloudinaryService.delete(recipeEntity.getPicture().getPublicId())) {
            this.recipeRepository.delete(recipeEntity);
        }

        this.cacheCleaner.removeCachedRecipes();
    }

    @Override
    public boolean isOwner(Long id, String username) {

        RecipeEntity recipe = findById(id);

        UserEntity user = this.userService.findByUsername(username);

        return isAdmin(user) || user.getUsername().equals(recipe.getAuthor().getUsername());
    }

    @Override
    public RecipeEntity findById(Long recipeId) {
        return this.recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ObjectNotFoundException("Recipe with id " + recipeId + " was not found!"));
    }

    private boolean isAdmin(UserEntity user) {
        return user.getRoles()
                .stream()
                .map(UserRoleEntity::getRole)
                .anyMatch(userRoleEnum -> userRoleEnum == UserRoleEnum.ADMIN);
    }
}
