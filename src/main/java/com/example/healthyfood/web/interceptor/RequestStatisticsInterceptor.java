package com.example.healthyfood.web.interceptor;

import com.example.healthyfood.service.RequestStatisticsService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestStatisticsInterceptor implements HandlerInterceptor {

    private final RequestStatisticsService requestStatisticsService;

    public RequestStatisticsInterceptor(RequestStatisticsService requestStatisticsService) {
        this.requestStatisticsService = requestStatisticsService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        this.requestStatisticsService.onRequest();

        return true;
    }
}
