package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.CommentEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.model.service.CommentAddServiceModel;
import com.example.healthyfood.model.view.CommentViewModel;
import com.example.healthyfood.repository.CommentRepository;
import com.example.healthyfood.service.CommentService;
import com.example.healthyfood.service.RecipeService;
import com.example.healthyfood.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final RecipeService recipeService;
    private final UserService userService;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(RecipeService recipeService, UserService userService, CommentRepository commentRepository) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @Transactional
    @Override
    public List<CommentViewModel> getComments(Long recipeId, String username) {

        RecipeEntity recipe = this.recipeService.findById(recipeId);

        return recipe.getComments()
                .stream()
                .map(commentEntity -> mapAsComment(commentEntity, username))
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewModel addComment(CommentAddServiceModel commentServiceModel) {

        UserEntity author = this.userService.findByUsername(commentServiceModel.getAuthor());

        RecipeEntity recipe = this.recipeService.findById(commentServiceModel.getRecipeId());

        CommentEntity commentEntity = new CommentEntity()
                .setAuthor(author)
                .setRecipe(recipe)
                .setMessage(commentServiceModel.getMessage())
                .setCreated(LocalDateTime.now());

        CommentEntity savedComment = this.commentRepository.save(commentEntity);

        return mapAsComment(savedComment, commentServiceModel.getAuthor());
    }

    private CommentViewModel mapAsComment(CommentEntity comment, String username) {

        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.setId(comment.getId())
                .setAuthor(comment.getAuthor().getUsername())
                .setCreated(comment.getCreated())
                .setMessage(comment.getMessage())
                .setCanDelete(comment.getAuthor().getUsername().equals(username))
                .setAuthorProfilePictureUrl(comment.getAuthor().getProfilePicture().getUrl());

        return commentViewModel;
    }
}
