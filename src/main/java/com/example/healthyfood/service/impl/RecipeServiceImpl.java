package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.view.RecipeHomeSummaryViewModel;
import com.example.healthyfood.repository.RecipeRepository;
import com.example.healthyfood.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final ModelMapper modelMapper;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ModelMapper modelMapper) {
        this.recipeRepository = recipeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeHomeSummaryViewModel> getLastSixRecipeViews() {

        return this.recipeRepository.findLastSixRecipesOrderByCreatedDesc()
                .stream()
                .map(recipeEntity -> this.modelMapper.map(recipeEntity, RecipeHomeSummaryViewModel.class))
                .collect(Collectors.toList());
    }
}
