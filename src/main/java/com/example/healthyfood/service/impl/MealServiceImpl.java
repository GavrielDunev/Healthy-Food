package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.MealEntity;
import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.repository.MealRepository;
import com.example.healthyfood.service.MealService;
import com.example.healthyfood.service.PictureService;
import com.example.healthyfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public MealServiceImpl(MealRepository mealRepository, PictureService pictureService, UserService userService, ModelMapper modelMapper) {
        this.mealRepository = mealRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeSummaryViewModel> getAllBreakfastViews() {

        return this.mealRepository.findAllBreakfastOrderByCreatedDesc()
                .stream()
                .map(mealEntity -> this.modelMapper.map(mealEntity, RecipeSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeSummaryViewModel> getAllLunchViews() {

        return this.mealRepository.findAllLunchOrderByCreatedDesc()
                .stream()
                .map(mealEntity -> this.modelMapper.map(mealEntity, RecipeSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeSummaryViewModel> getAllDinnerViews() {

        return this.mealRepository.findAllDinnerOrderByCreatedDesc()
                .stream()
                .map(mealEntity -> this.modelMapper.map(mealEntity, RecipeSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveMeal(RecipeAddServiceModel recipeAddServiceModel, String username) throws IOException {

        PictureEntity picture = this.pictureService.createPicture(recipeAddServiceModel.getPicture());
        UserEntity author = this.userService.findByUsername(username);

        RecipeEntity recipe = this.modelMapper.map(recipeAddServiceModel, RecipeEntity.class);
        recipe.setPicture(picture)
                .setAuthor(author)
                .setCreated(LocalDateTime.now())
                .setMeal(null);

        MealEntity mealEntity = new MealEntity()
                .setRecipe(recipe)
                .setCategory(recipeAddServiceModel.getCategory());

        this.mealRepository.save(mealEntity);
    }

}
