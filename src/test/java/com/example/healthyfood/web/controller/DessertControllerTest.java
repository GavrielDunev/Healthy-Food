package com.example.healthyfood.web.controller;

import com.example.healthyfood.service.DessertService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser("testUser")
@SpringBootTest
@AutoConfigureMockMvc
class DessertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DessertService dessertService;

    @Autowired
    private ModelMapper modelMapper;

    private MockMultipartFile mockMultipartFile;

    @Test
    public void testGetDesserts() throws Exception {

        this.mockMvc.perform(get("/desserts"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipes"));
    }

    @Test
    public void testGetDessertAdd() throws Exception {

        this.mockMvc.perform(get("/desserts/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-recipe"));
    }

}