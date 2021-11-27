package com.example.healthyfood.service;

import javax.servlet.http.HttpServletRequest;

public interface UserProfileViewsService {

    void onRequest(HttpServletRequest httpServletRequest);

    int getViews(String username);
}
