package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.view.RecipeAllSummaryViewModel;
import com.example.healthyfood.repository.DessertRepository;
import com.example.healthyfood.service.DessertService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DessertServiceImpl implements DessertService {

    private final DessertRepository dessertRepository;
    private final ModelMapper modelMapper;

    public DessertServiceImpl(DessertRepository dessertRepository, ModelMapper modelMapper) {
        this.dessertRepository = dessertRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeAllSummaryViewModel> getAllDessertViews() {

        return this.dessertRepository.findAllOrderByCreatedDesc()
                .stream()
                .map(dessertEntity -> this.modelMapper.map(dessertEntity, RecipeAllSummaryViewModel.class))
                .collect(Collectors.toList());
    }
}
