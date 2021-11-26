package com.example.healthyfood.service;

import com.example.healthyfood.model.view.RequestStatisticsViewModel;

public interface RequestStatisticsService {

    void onRequest();

    RequestStatisticsViewModel getStatistics();
}
