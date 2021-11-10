package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.DessertEntity;
import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.repository.DessertRepository;
import com.example.healthyfood.service.DessertService;
import com.example.healthyfood.service.PictureService;
import com.example.healthyfood.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DessertServiceImpl implements DessertService {

    private final DessertRepository dessertRepository;
    private final PictureService pictureService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public DessertServiceImpl(DessertRepository dessertRepository, PictureService pictureService, UserService userService, ModelMapper modelMapper) {
        this.dessertRepository = dessertRepository;
        this.pictureService = pictureService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllDessertViews() {

        return this.dessertRepository.findAllOrderByCreatedDesc()
                .stream()
                .map(dessertEntity -> this.modelMapper.map(dessertEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void saveDessert(RecipeAddServiceModel recipeAddServiceModel, String username) throws IOException {

        PictureEntity picture = this.pictureService.createPicture(recipeAddServiceModel.getPicture());
        UserEntity author = this.userService.findByUsername(username);

        RecipeEntity recipe = this.modelMapper.map(recipeAddServiceModel, RecipeEntity.class);
        recipe.setPicture(picture).setAuthor(author)
                .setCreated(LocalDateTime.now());

        DessertEntity dessertEntity = new DessertEntity()
                .setRecipe(recipe);

        this.dessertRepository.save(dessertEntity);
    }
}
