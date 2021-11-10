package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.DrinkEntity;
import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.repository.DrinkRepository;
import com.example.healthyfood.service.DrinkService;
import com.example.healthyfood.service.PictureService;
import com.example.healthyfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public DrinkServiceImpl(DrinkRepository drinkRepository, PictureService pictureService, UserService userService, ModelMapper modelMapper) {
        this.drinkRepository = drinkRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllDrinkViews() {

        return this.drinkRepository.findAllOrderByCreatedDesc()
                .stream()
                .map(drinkEntity -> this.modelMapper.map(drinkEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveDrink(RecipeAddServiceModel recipeAddServiceModel, String username) throws IOException {

        PictureEntity picture = this.pictureService.createPicture(recipeAddServiceModel.getPicture());
        UserEntity author = this.userService.findByUsername(username);

        RecipeEntity recipe = this.modelMapper.map(recipeAddServiceModel, RecipeEntity.class);
        recipe.setPicture(picture)
                .setAuthor(author)
                .setCreated(LocalDateTime.now());

        DrinkEntity drink = new DrinkEntity()
                .setRecipe(recipe);

        this.drinkRepository.save(drink);
    }
}
