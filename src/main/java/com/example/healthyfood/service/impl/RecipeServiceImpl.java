package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.service.RecipeEditServiceModel;
import com.example.healthyfood.model.view.RecipeDetailsViewModel;
import com.example.healthyfood.model.view.RecipeEditViewModel;
import com.example.healthyfood.model.view.RecipeHomeSummaryViewModel;
import com.example.healthyfood.repository.RecipeRepository;
import com.example.healthyfood.service.PictureService;
import com.example.healthyfood.service.RecipeService;
import com.example.healthyfood.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final PictureService pictureService;
    private final ModelMapper modelMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, PictureService pictureService, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.pictureService = pictureService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeHomeSummaryViewModel> getLastSixRecipeViews() {

        return this.recipeRepository.findLastSixRecipesOrderByCreatedDesc()
                .stream()
                .map(recipeEntity -> this.modelMapper.map(recipeEntity, RecipeHomeSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public RecipeDetailsViewModel getRecipeDetailsViewById(Long id) {

        return this.recipeRepository.findById(id)
                .map(recipeEntity -> this.modelMapper.map(recipeEntity, RecipeDetailsViewModel.class))
                .orElse(null);

    }

    @Override
    public RecipeEditViewModel getRecipeEditViewById(Long id) {

        RecipeEntity recipe = this.recipeRepository.findById(id).orElse(null);
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

        RecipeEntity recipeEntity = this.recipeRepository.findById(recipeEditServiceModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Recipe with id "
                        + recipeEditServiceModel.getId() + " was not found!"));


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
}
