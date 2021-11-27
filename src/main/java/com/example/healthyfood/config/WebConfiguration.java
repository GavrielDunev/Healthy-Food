package com.example.healthyfood.config;

import com.example.healthyfood.web.interceptor.UserProfileViewsInterceptor;
import com.example.healthyfood.web.interceptor.RequestStatisticsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final RequestStatisticsInterceptor requestStatisticsInterceptor;
    private final UserProfileViewsInterceptor userProfileViewsInterceptor;

    public WebConfiguration(RequestStatisticsInterceptor requestStatisticsInterceptor, UserProfileViewsInterceptor userProfileViewsInterceptor) {
        this.requestStatisticsInterceptor = requestStatisticsInterceptor;
        this.userProfileViewsInterceptor = userProfileViewsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.requestStatisticsInterceptor);
        registry.addInterceptor(this.userProfileViewsInterceptor);
    }
}
