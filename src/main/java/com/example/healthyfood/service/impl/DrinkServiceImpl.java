package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.repository.DrinkRepository;
import com.example.healthyfood.service.DrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkServiceImpl implements DrinkService {

    private final DrinkRepository drinkRepository;
    private final ModelMapper modelMapper;

    public DrinkServiceImpl(DrinkRepository drinkRepository, ModelMapper modelMapper) {
        this.drinkRepository = drinkRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllDrinkViews() {

        return this.drinkRepository.findAllOrderByCreatedDesc()
                .stream()
                .map(drinkEntity -> this.modelMapper.map(drinkEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }
}
