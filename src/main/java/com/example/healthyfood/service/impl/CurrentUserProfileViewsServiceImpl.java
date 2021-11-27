package com.example.healthyfood.service.impl;

import com.example.healthyfood.service.CurrentUserProfileViewsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CurrentUserProfileViewsServiceImpl implements CurrentUserProfileViewsService {

    private int viewsByAdmins;

    @Override
    public void onRequest(HttpServletRequest httpServletRequest) {

        String requestedUrl = httpServletRequest.getRequestURL().toString();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (requestedUrl.contains("/users/profile/")
        && authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            viewsByAdmins++;
        }
    }

    @Override
    public int getViewsByAdmins() {
        return this.viewsByAdmins;
    }

}
