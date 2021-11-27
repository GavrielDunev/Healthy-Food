package com.example.healthyfood.service.impl;

import com.example.healthyfood.service.CurrentUserProfileViewsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CurrentUserProfileViewsServiceImpl implements CurrentUserProfileViewsService {

    private int views;

    @Override
    public void onRequest(HttpServletRequest httpServletRequest) {

        String requestedUrl = httpServletRequest.getRequestURL().toString();

        if (requestedUrl.contains("/users/profile/")) {
            views++;
        }
    }

    @Override
    public int getViews() {
        return this.views;
    }

}
