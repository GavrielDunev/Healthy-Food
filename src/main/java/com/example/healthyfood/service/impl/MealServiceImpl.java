package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.repository.MealRepository;
import com.example.healthyfood.service.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;

    public MealServiceImpl(MealRepository mealRepository, ModelMapper modelMapper) {
        this.mealRepository = mealRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllBreakfastViews() {

        return this.mealRepository.findAllBreakfastOrderByCreatedDesc()
                .stream()
                .map(mealEntity -> this.modelMapper.map(mealEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllLunchViews() {

        return this.mealRepository.findAllLunchOrderByCreatedDesc()
                .stream()
                .map(mealEntity -> this.modelMapper.map(mealEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllDinnerViews() {

        return this.mealRepository.findAllDinnerOrderByCreatedDesc()
                .stream()
                .map(mealEntity -> this.modelMapper.map(mealEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }
}
