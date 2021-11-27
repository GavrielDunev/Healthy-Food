package com.example.healthyfood.service;

import javax.servlet.http.HttpServletRequest;

public interface CurrentUserProfileViewsService {

    void onRequest(HttpServletRequest httpServletRequest);

    int getViewsByAdmins();
}
