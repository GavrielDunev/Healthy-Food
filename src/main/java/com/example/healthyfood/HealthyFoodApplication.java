package com.example.healthyfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthyFoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyFoodApplication.class, args);
    }

}
