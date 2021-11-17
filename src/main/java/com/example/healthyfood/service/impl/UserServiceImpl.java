package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.UserRoleEntity;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.model.service.*;
import com.example.healthyfood.model.view.AdminAddRoleViewModel;
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

    @Override
    public boolean isCurrentPasswordValid(String username, String currentPassword) {

        UserEntity userEntity = findByUsername(username);

        return this.passwordEncoder.matches(currentPassword, userEntity.getPassword());
    }

    @Override
    public void changePassword(String username, UserChangePasswordServiceModel userChangePasswordServiceModel) {
        UserEntity userEntity = findByUsername(username);

        userEntity.setPassword(this.passwordEncoder.encode(userChangePasswordServiceModel.getNewPassword()));

        this.userRepository.save(userEntity);
    }

    @Override
    public boolean isCurrentUser(String currentUserUsername, String username) {
        return currentUserUsername.equals(username);
    }

    @Override
    public List<AdminAddRoleViewModel> getAllUserUsernamesWithoutCurrent(String currentUser) {

        return this.userRepository.findAllUsernames()
                .stream()
                .filter(s -> !s.equals(currentUser))
                .map(s -> new AdminAddRoleViewModel().setUsername(s))
                .collect(Collectors.toList());
    }

    @Override
    public void addRole(AdminAddRoleServiceModel addRoleServiceModel) {

        UserRoleEnum role = addRoleServiceModel.getUserRole();

        UserEntity userEntity = findByUsername(addRoleServiceModel.getUsername());

        UserRoleEntity adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);

        if (role.name().equals("ADMIN")) {
            userEntity.getRoles().add(adminRole);
        } else if (role.name().equals("USER")) {
            userEntity.getRoles()
                    .removeIf(userRoleEntity -> userRoleEntity.getRole().name().equals("ADMIN"));
        }

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
