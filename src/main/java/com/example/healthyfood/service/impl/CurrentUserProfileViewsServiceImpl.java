package com.example.healthyfood.service.impl;

import com.example.healthyfood.service.CurrentUserProfileViewsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;


@Service
public class CurrentUserProfileViewsServiceImpl implements CurrentUserProfileViewsService {

    private int views;

    @Override
    public void onRequest(HttpServletRequest httpServletRequest) {

        String requestedUrl = httpServletRequest.getRequestURL().toString();

        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        if (requestedUrl.contains("/users/profile/" + principal.getName())) {
            views++;
        }
    }

    @Override
    public int getViews() {
        return this.views;
    }

}
