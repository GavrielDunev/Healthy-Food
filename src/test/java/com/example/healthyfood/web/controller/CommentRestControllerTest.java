package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.CommentAddBindingModel;
import com.example.healthyfood.model.entity.CommentEntity;
import com.example.healthyfood.model.entity.PictureEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.entity.enums.RecipeDifficultyEnum;
import com.example.healthyfood.repository.PictureRepository;
import com.example.healthyfood.repository.RecipeRepository;
import com.example.healthyfood.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser("testUser")
@SpringBootTest
@AutoConfigureMockMvc
class CommentRestControllerTest {

    private static final String COMMENT_1 = "First comment";
    private static final String COMMENT_2 = "Second comment";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PictureRepository pictureRepository;

    private UserEntity testUser;

    @BeforeEach
    public void setUp() {

        PictureEntity picture = new PictureEntity()
                .setPublicId("35125251")
                .setUrl("testUrl");

        this.pictureRepository.save(picture);

        testUser = new UserEntity()
                .setUsername("testUser")
                .setFirstName("First")
                .setLastName("Last")
                .setEmail("mail")
                .setPassword("password")
                .setProfilePicture(picture);

        testUser = this.userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        this.recipeRepository.deleteAll();
        this.userRepository.deleteAll();
    }

    @Test
    public void testGetComments() throws Exception {

        var recipe = initComments(initRecipe());

        mockMvc.perform(get("/api/" + recipe.getId() + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].message", is(COMMENT_1)))
                .andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    public void testCreateComment() throws Exception {

        CommentAddBindingModel testComment = new CommentAddBindingModel()
                .setMessage(COMMENT_1);

        var emptyRecipe = initRecipe();

        this.mockMvc.perform(post("/api/" + emptyRecipe.getId() + "/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testComment))
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf())
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", matchesPattern("/api/" + emptyRecipe.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }

    private RecipeEntity initRecipe() {

        RecipeEntity recipe = new RecipeEntity()
                .setTitle("Title")
                .setPrepTime(1)
                .setCookTime(1)
                .setDescription("Description")
                .setCreated(LocalDateTime.now())
                .setDifficulty(RecipeDifficultyEnum.EASY)
                .setIngredients("Ingredients")
                .setInstructions("Instructions");

        return this.recipeRepository.save(recipe);
    }

    private RecipeEntity initComments(RecipeEntity recipe) {

        CommentEntity comment1 = new CommentEntity()
                .setMessage(COMMENT_1)
                .setCreated(LocalDateTime.now())
                .setAuthor(this.testUser)
                .setRecipe(recipe);

        CommentEntity comment2 = new CommentEntity()
                .setMessage(COMMENT_2)
                .setCreated(LocalDateTime.now())
                .setAuthor(this.testUser)
                .setRecipe(recipe);

        recipe.setComments(List.of(comment1, comment2));

        return this.recipeRepository.save(recipe);
    }
}
