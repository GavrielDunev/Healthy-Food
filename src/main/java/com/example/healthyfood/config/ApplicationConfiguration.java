package com.example.healthyfood.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Map;

@Configuration
public class ApplicationConfiguration {

    private final CloudinaryConfiguration cloudinaryConfiguration;

    public ApplicationConfiguration(CloudinaryConfiguration cloudinaryConfiguration) {
        this.cloudinaryConfiguration = cloudinaryConfiguration;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(Map.of(
                "cloud_name", this.cloudinaryConfiguration.getCloudName(),
                "api_key", this.cloudinaryConfiguration.getApiKey(),
                "api_secret", this.cloudinaryConfiguration.getApiSecret()
        ));
    }
}
