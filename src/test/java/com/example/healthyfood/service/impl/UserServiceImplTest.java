package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.*;
import com.example.healthyfood.model.entity.enums.MealCategoryEnum;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.model.service.UserProfileEditServiceModel;
import com.example.healthyfood.model.service.UserUploadPhotoServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.model.view.UserProfileEditViewModel;
import com.example.healthyfood.repository.UserRepository;
import com.example.healthyfood.repository.UserRoleRepository;
import com.example.healthyfood.service.CloudinaryService;
import com.example.healthyfood.service.PictureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private RecipeEntity recipe1;

    private RecipeEntity recipe2;

    private UserServiceImpl testService;

    private UserEntity testUser;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private HealthyFoodUserServiceImpl healthyFoodUserService;

    @Mock
    private CloudinaryService cloudinaryService;

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {

        this.testService = new UserServiceImpl(this.userRepository, this.userRoleRepository,
                this.pictureService, this.passwordEncoder, this.healthyFoodUserService,
                this.cloudinaryService, this.modelMapper);

        MealEntity testMeal = new MealEntity()
                .setCategory(MealCategoryEnum.DINNER);

        PictureEntity testPicture = (PictureEntity) new PictureEntity()
                .setPublicId("24151512")
                .setUrl("dadad")
                .setId(2L);

        this.recipe1 = new RecipeEntity()
                .setTitle("First recipe")
                .setPrepTime(1)
                .setCookTime(1)
                .setDescription("First description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.EASY)
                .setIngredients("First ingredients")
                .setInstructions("First instructions")
                .setMeal(testMeal)
                .setPicture(testPicture);

        this.recipe2 = new RecipeEntity()
                .setTitle("Second recipe")
                .setPrepTime(2)
                .setCookTime(2)
                .setDescription("Second description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.MEDIUM)
                .setIngredients("Second ingredients")
                .setInstructions("Second instructions");

        UserRoleEntity user = new UserRoleEntity()
                .setRole(UserRoleEnum.USER);
        UserRoleEntity admin = new UserRoleEntity()
                .setRole(UserRoleEnum.ADMIN);

        this.testUser = new UserEntity()
                .setUsername("test")
                .setPassword("213451521")
                .setEmail("email")
                .setFirstName("first name")
                .setLastName("last name")
                .setRecipes(Set.of(this.recipe1, this.recipe2))
                .setRoles(Set.of(user, admin))
                .setProfilePicture(testPicture);
    }

    @Test
    public void testFindAllUserRecipes() {

        when(this.userRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        List<RecipeSummaryViewModel> result = this.testService.findAllUserRecipes(this.testUser.getUsername());

        assertEquals(result.size(), 2);
    }

    @Test
    public void testUploadProfilePicture() throws IOException {

        when(this.userRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        UserUploadPhotoServiceModel testServiceModel = new UserUploadPhotoServiceModel()
                .setPicture(new MultipartFile() {
                    @Override
                    public String getName() {
                        return null;
                    }

                    @Override
                    public String getOriginalFilename() {
                        return null;
                    }

                    @Override
                    public String getContentType() {
                        return null;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public long getSize() {
                        return 0;
                    }

                    @Override
                    public byte[] getBytes() throws IOException {
                        return new byte[0];
                    }

                    @Override
                    public InputStream getInputStream() throws IOException {
                        return null;
                    }

                    @Override
                    public void transferTo(File dest) throws IOException, IllegalStateException {

                    }
                });

        when(this.cloudinaryService.delete(any(String.class)))
                .thenReturn(true);

        this.testService.uploadProfilePicture(this.testUser.getUsername(), testServiceModel);

        verify(this.pictureService, times(1)).createPicture(testServiceModel.getPicture());
        verify(this.userRepository, times(1)).save(this.testUser);
        verify(this.cloudinaryService,times(1)).delete(any(String.class));
        verify(this.pictureService, times(1)).deletePicture(any(Long.class));
    }

    @Test
    public void testDeleteProfilePicture() {

        when(this.userRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        when(this.pictureService.findById(1L))
                .thenReturn(this.testUser.getProfilePicture());

        when(this.userRepository.save(this.testUser))
                .thenReturn(this.testUser);

        when(this.cloudinaryService.delete(any(String.class)))
                .thenReturn(true);

        this.testService.deleteProfilePicture(this.testUser.getUsername());

        verify(this.pictureService, times(1)).findById(1L);
        verify(this.userRepository, times(1)).save(this.testUser);
        verify(this.cloudinaryService,times(1)).delete(any(String.class));
        verify(this.pictureService, times(1)).deletePicture(any(Long.class));
    }

    @Test
    public void testGetUserProfileEditViewModel() {

        when(this.userRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        UserProfileEditViewModel testViewModel = new UserProfileEditViewModel()
                .setEmail(this.testUser.getEmail())
                        .setFirstName(this.testUser.getFirstName())
                                .setLastName(this.testUser.getLastName());

        when(this.modelMapper.map(this.testUser, UserProfileEditViewModel.class))
                .thenReturn(testViewModel);

        UserProfileEditViewModel result = this.testService.getUserProfileEditViewModel(this.testUser.getUsername());

        assertEquals(result.getFirstName(), testViewModel.getFirstName());
        assertEquals(result.getLastName(), testViewModel.getLastName());
        assertEquals(result.getEmail(), testViewModel.getEmail());
    }

    @Test
    public void testEditUserProfile() {

        when(this.userRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        UserProfileEditServiceModel testServiceModel = new UserProfileEditServiceModel()
                .setEmail(this.testUser.getEmail())
                .setFirstName(this.testUser.getFirstName())
                .setLastName(this.testUser.getLastName());

        this.testService.editUserProfile(this.testUser.getUsername(), testServiceModel);

        verify(this.userRepository, times(1)).findByUsername(this.testUser.getUsername());
        verify(this.userRepository, times(1)).save(this.testUser);
    }
}