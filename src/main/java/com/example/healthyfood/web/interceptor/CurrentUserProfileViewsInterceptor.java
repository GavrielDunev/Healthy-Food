package com.example.healthyfood.web.interceptor;

import com.example.healthyfood.service.CurrentUserProfileViewsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CurrentUserProfileViewsInterceptor implements HandlerInterceptor {

    private final CurrentUserProfileViewsService currentUserProfileViewsService;

    public CurrentUserProfileViewsInterceptor(CurrentUserProfileViewsService currentUserProfileViewsService) {
        this.currentUserProfileViewsService = currentUserProfileViewsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        this.currentUserProfileViewsService.onRequest(request);

        return true;
    }
}
