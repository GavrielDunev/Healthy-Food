package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.view.StatisticsViewModel;
import com.example.healthyfood.service.StatisticsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private int authenticatedRequests, anonymousRequests;

    @Override
    public void onRequest() {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication != null && (authentication.getPrincipal() instanceof UserDetails)) {
            authenticatedRequests++;
        } else {
            anonymousRequests++;
        }

    }

    @Override
    public StatisticsViewModel getStatistics() {
        return new StatisticsViewModel(authenticatedRequests, anonymousRequests);
    }
}
