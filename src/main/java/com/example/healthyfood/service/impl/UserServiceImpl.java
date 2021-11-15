package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.binding.UserProfileEditBindingModel;
import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.UserRoleEntity;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.model.service.UserProfileEditServiceModel;
import com.example.healthyfood.model.service.UserRegisterServiceModel;
import com.example.healthyfood.model.service.UserUploadPhotoServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.model.view.UserProfileEditViewModel;
import com.example.healthyfood.repository.UserRepository;
import com.example.healthyfood.repository.UserRoleRepository;
import com.example.healthyfood.service.CloudinaryService;
import com.example.healthyfood.service.PictureService;
import com.example.healthyfood.service.UserService;
import com.example.healthyfood.web.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PictureService pictureService;
    private final PasswordEncoder passwordEncoder;
    private final HealthyFoodUserServiceImpl healthyFoodUserService;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PictureService pictureService, PasswordEncoder passwordEncoder, HealthyFoodUserServiceImpl healthyFoodUserService, CloudinaryService cloudinaryService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.pictureService = pictureService;
        this.passwordEncoder = passwordEncoder;
        this.healthyFoodUserService = healthyFoodUserService;
        this.cloudinaryService = cloudinaryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isUsernameFree(String username) {
        return !this.userRepository.existsByUsernameIgnoreCase(username);
    }

    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel) {

        UserRoleEntity userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);
        UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);

        PictureEntity defaultProfilePicture = this.pictureService.findById(1L);

        UserEntity newUser = new UserEntity()
                .setEmail(userRegisterServiceModel.getEmail())
                .setFirstName(userRegisterServiceModel.getFirstName())
                .setLastName(userRegisterServiceModel.getLastName())
                .setPassword(this.passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setUsername(userRegisterServiceModel.getUsername())
                .setProfilePicture(defaultProfilePicture);

        Set<UserRoleEntity> roles = new HashSet<>();
        roles.add(userRole);

        if (this.userRepository.count() == 1) {
            roles.add(adminRole);
        }

        newUser.setRoles(roles);

        this.userRepository.save(newUser);

        UserDetails principal = this.healthyFoodUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                newUser.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public UserEntity findByUsername(String username) {

        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User with username " + username + " was not found!"));
    }

    @Override
    public List<RecipeSummaryViewModel> findAllUserRecipes(String username) {

        UserEntity userEntity = findByUsername(username);

        return userEntity.getRecipes()
                .stream()
                .sorted((r1, r2) -> r2.getCreated().compareTo(r1.getCreated()))
                .map(recipeEntity -> this.modelMapper.map(recipeEntity, RecipeSummaryViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void uploadProfilePicture(String username, UserUploadPhotoServiceModel userUploadPhotoServiceModel) throws IOException {

        UserEntity userEntity = findByUsername(username);

        PictureEntity newPicture = this.pictureService.createPicture(userUploadPhotoServiceModel.getPicture());

        userEntity.setProfilePicture(newPicture);
        this.userRepository.save(userEntity);

        deleteCurrentProfilePicture(userEntity);
    }

    @Override
    public void deleteProfilePicture(String username) {

        UserEntity userEntity = findByUsername(username);

        PictureEntity defaultProfilePicture = this.pictureService.findById(1L);

        userEntity.setProfilePicture(defaultProfilePicture);
        this.userRepository.save(userEntity);

        deleteCurrentProfilePicture(userEntity);
    }

    @Override
    public UserProfileEditViewModel getUserProfileEditViewModel(String username) {

        UserEntity userEntity = findByUsername(username);

        return this.modelMapper.map(userEntity, UserProfileEditViewModel.class);
    }

    @Override
    public void editUserProfile(String username, UserProfileEditServiceModel userProfileEditServiceModel) {
        UserEntity userEntity = findByUsername(username);

        userEntity.setFirstName(userProfileEditServiceModel.getFirstName())
                .setLastName(userProfileEditServiceModel.getLastName())
                .setEmail(userProfileEditServiceModel.getEmail());

        this.userRepository.save(userEntity);
    }

    private void deleteCurrentProfilePicture(UserEntity userEntity) {

        PictureEntity currentProfilePicture = userEntity.getProfilePicture();

        if (currentProfilePicture.getId() != 1) {
            this.cloudinaryService.delete(currentProfilePicture.getPublicId());
            this.pictureService.deletePicture(currentProfilePicture.getId());
        }
    }
}
