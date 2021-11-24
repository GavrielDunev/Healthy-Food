package com.example.healthyfood.service;

import com.example.healthyfood.model.service.CommentServiceModel;
import com.example.healthyfood.model.view.CommentViewModel;

import java.util.List;

public interface CommentService {

    List<CommentViewModel> getComments(Long recipeId, String username);

    CommentViewModel addComment(CommentServiceModel commentServiceModel);
}
