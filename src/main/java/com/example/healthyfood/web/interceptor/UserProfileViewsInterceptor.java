package com.example.healthyfood.web.interceptor;

import com.example.healthyfood.service.UserProfileViewsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserProfileViewsInterceptor implements HandlerInterceptor {

    private final UserProfileViewsService userProfileViewsService;

    public UserProfileViewsInterceptor(UserProfileViewsService userProfileViewsService) {
        this.userProfileViewsService = userProfileViewsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        this.userProfileViewsService.onRequest(request);

        return true;
    }
}
