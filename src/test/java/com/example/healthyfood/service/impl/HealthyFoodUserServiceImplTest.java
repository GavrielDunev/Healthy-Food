package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.UserRoleEntity;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HealthyFoodUserServiceImplTest {

    private UserEntity testUser;
    private UserRoleEntity adminRole, userRole;

    private HealthyFoodUserServiceImpl testService;

    @Mock
    private UserRepository mockedUserRepository;

    @BeforeEach
    public void init() {

        this.testService = new HealthyFoodUserServiceImpl(this.mockedUserRepository);

        this.adminRole = new UserRoleEntity()
                .setRole(UserRoleEnum.ADMIN);

        this.userRole = new UserRoleEntity()
                .setRole(UserRoleEnum.USER);

        this.testUser = new UserEntity()
                .setUsername("testUser")
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("mail")
                .setPassword("password")
                .setRoles(Set.of(this.adminRole, this.userRole));
    }

    @Test
    public void testUserNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> this.testService.loadUserByUsername("invalid_username"));
    }

    @Test
    public void testUserFound() {

        when(this.mockedUserRepository.findByUsername(this.testUser.getUsername()))
                .thenReturn(Optional.of(this.testUser));

        var actualUser = this.testService.loadUserByUsername(this.testUser.getUsername());

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";

        String actualRoles = actualUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        assertEquals(actualUser.getUsername(), this.testUser.getUsername());
        assertEquals(actualRoles, expectedRoles);
    }
}