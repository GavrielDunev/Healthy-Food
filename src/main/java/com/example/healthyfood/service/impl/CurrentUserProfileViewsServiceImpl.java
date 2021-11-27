package com.example.healthyfood.service.impl;

import com.example.healthyfood.service.CurrentUserProfileViewsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (requestedUrl.contains("/users/profile/")) {
            views++;
        }
    }

    @Override
    public int getViews() {
        return this.views;
    }

}
