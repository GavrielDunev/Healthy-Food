package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.CommentEntity;
import com.example.healthyfood.model.entity.RecipeEntity;
import com.example.healthyfood.model.service.CommentServiceModel;
import com.example.healthyfood.model.view.CommentViewModel;
import com.example.healthyfood.repository.CommentRepository;
import com.example.healthyfood.service.CommentService;
import com.example.healthyfood.service.RecipeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final RecipeService recipeService;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(RecipeService recipeService, CommentRepository commentRepository) {
        this.recipeService = recipeService;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<CommentViewModel> getComments(Long recipeId, String username) {

        RecipeEntity recipe = this.recipeService.findById(recipeId);

        return recipe.getComments()
                .stream()
                .map(commentEntity -> mapAsComment(commentEntity, username))
                .collect(Collectors.toList());
    }

    @Override
    public CommentViewModel addComment(CommentServiceModel commentServiceModel) {
        //TODO:
        return null;
    }

    private CommentViewModel mapAsComment(CommentEntity comment, String username) {

        CommentViewModel commentViewModel = new CommentViewModel();

        commentViewModel.setId(comment.getId())
                .setAuthor(comment.getAuthor().getUsername())
                .setCreated(comment.getCreated())
                .setText(comment.getText())
                .setCanDelete(comment.getAuthor().getUsername().equals(username))
                .setAuthorProfilePictureUrl(comment.getAuthor().getProfilePicture().getUrl());

        return commentViewModel;
    }
}
