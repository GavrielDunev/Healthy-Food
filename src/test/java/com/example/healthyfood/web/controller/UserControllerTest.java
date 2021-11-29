package com.example.healthyfood.web.controller;

import com.example.healthyfood.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public UserRepository userRepository;

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
                .param("username", "testUser")
                .param("password", "12345678")
                .param("confirmPassword", "12345678")
                .param("firstName", "First")
                .param("lastName", "Last")
                .param("email", "testUser@gmail.com")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());
    }
}