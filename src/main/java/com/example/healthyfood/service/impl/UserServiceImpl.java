package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.UserRoleEntity;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.model.service.UserRegisterServiceModel;
import com.example.healthyfood.repository.PictureRepository;
import com.example.healthyfood.repository.UserRepository;
import com.example.healthyfood.repository.UserRoleRepository;
import com.example.healthyfood.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PictureRepository pictureRepository;
    private final PasswordEncoder passwordEncoder;
    private final HealthyFoodUserServiceImpl healthyFoodUserService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PictureRepository pictureRepository, PasswordEncoder passwordEncoder, HealthyFoodUserServiceImpl healthyFoodUserService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.pictureRepository = pictureRepository;
        this.passwordEncoder = passwordEncoder;
        this.healthyFoodUserService = healthyFoodUserService;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {

        UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);
        PictureEntity defaultProfilePicture = this.pictureRepository.getById(1L);

        UserEntity newUser = new UserEntity()
                .setEmail(userRegisterServiceModel.getEmail())
                .setFirstName(userRegisterServiceModel.getFirstName())
                .setLastName(userRegisterServiceModel.getLastName())
                .setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setUsername(userRegisterServiceModel.getUsername())
                .setProfilePicture(defaultProfilePicture)
                .setRoles(Set.of(userRole));

        this.userRepository.save(newUser);

        UserDetails principal = this.healthyFoodUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                newUser.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
