package com.example.healthyfood.config;

import com.example.healthyfood.web.interceptor.CurrentUserProfileViewsInterceptor;
import com.example.healthyfood.web.interceptor.RequestStatisticsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final RequestStatisticsInterceptor requestStatisticsInterceptor;
    private final CurrentUserProfileViewsInterceptor currentUserProfileViewsInterceptor;

    public WebConfiguration(RequestStatisticsInterceptor requestStatisticsInterceptor, CurrentUserProfileViewsInterceptor currentUserProfileViewsInterceptor) {
        this.requestStatisticsInterceptor = requestStatisticsInterceptor;
        this.currentUserProfileViewsInterceptor = currentUserProfileViewsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(this.requestStatisticsInterceptor);
        registry.addInterceptor(this.currentUserProfileViewsInterceptor);
    }
}
