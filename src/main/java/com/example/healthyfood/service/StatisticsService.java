package com.example.healthyfood.service;

import com.example.healthyfood.model.view.StatisticsViewModel;

public interface StatisticsService {

    void onRequest();

    StatisticsViewModel getStatistics();
}
