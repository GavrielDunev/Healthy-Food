package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.UserRoleEntity;
import com.example.healthyfood.model.entity.enums.UserRoleEnum;
import com.example.healthyfood.repository.PictureRepository;
import com.example.healthyfood.repository.UserRepository;
import com.example.healthyfood.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String TEST_USER_USERNAME = "testUser";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void init() {

        UserRoleEntity adminRole = new UserRoleEntity()
                .setRole(UserRoleEnum.ADMIN);

        UserRoleEntity userRole = new UserRoleEntity()
                .setRole(UserRoleEnum.USER);

        this.userRoleRepository.saveAll(List.of(adminRole, userRole));

        PictureEntity picture = new PictureEntity()
                .setUrl("testUrl")
                .setPublicId("25251");

        this.pictureRepository.save(picture);
    }

    @AfterEach
    public void tearDown() {

        this.userRepository.deleteAll();
    }

    @Test
    public void testGetRegisterPage() throws Exception {

        this.mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void testRegisterUser() throws Exception {

        this.mockMvc.perform(post("/users/register")
                .param("username", TEST_USER_USERNAME)
                .param("password", "12345678")
                .param("confirmPassword", "12345678")
                .param("firstName", "First")
                .param("lastName", "Last")
                .param("email", "testUser@gmail.com")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());

        assertEquals(1, this.userRepository.count());

        Optional<UserEntity> newUserOpt = this.userRepository.findByUsername(TEST_USER_USERNAME);

        assertTrue(newUserOpt.isPresent());

        UserEntity newUser = newUserOpt.get();

        assertEquals(newUser.getUsername(), TEST_USER_USERNAME);
        assertTrue(this.passwordEncoder.matches("12345678", newUser.getPassword()));
        assertEquals(newUser.getFirstName(), "First");
        assertEquals(newUser.getLastName(), "Last");
        assertEquals(newUser.getEmail(), "testUser@gmail.com");
    }

    @Transactional
    @Test
    public void testGetLoginPage() throws Exception {

        this.mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}