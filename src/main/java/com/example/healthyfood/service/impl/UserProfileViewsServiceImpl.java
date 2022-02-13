package com.example.healthyfood.service.impl;

import com.example.healthyfood.service.UserProfileViewsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Hashtable;
import java.util.Map;

@Service
public class UserProfileViewsServiceImpl implements UserProfileViewsService {

    private final Map<String, Integer> users = new Hashtable<>();
    private static final String[] PATHS = new String[]{"upload-photo", "recipes", "edit-profile", "change-password"};

    @Override
    public void onRequest(HttpServletRequest httpServletRequest) {

        String requestedUrl = httpServletRequest.getRequestURL().toString();

        if (requestedUrl.contains("/users/profile/")
                && !requestedUrl.contains(PATHS[0])
                && !requestedUrl.contains(PATHS[1])
                && !requestedUrl.contains(PATHS[2])
                && !requestedUrl.contains(PATHS[3])) {

            String currentUser = requestedUrl.substring(requestedUrl.lastIndexOf("/") + 1);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (currentUser.equals(authentication.getName())) {
                return;
            }

            if (!this.users.containsKey(currentUser)) {
                this.users.put(currentUser, 1);
            } else {
                this.users.put(currentUser, this.users.get(currentUser) + 1);
            }

        }
    }

    @Override
    public int getViews(String username) {

        if (!this.users.containsKey(username)) {
            this.users.put(username, 0);
        }

        return this.users.get(username);
    }

}
