package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.CommentAddBindingModel;
import com.example.healthyfood.model.service.CommentAddServiceModel;
import com.example.healthyfood.model.validation.ApiError;
import com.example.healthyfood.model.view.CommentViewModel;
import com.example.healthyfood.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api/{recipeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(@PathVariable Long recipeId,
                                                              Principal principal) {

        return ResponseEntity.ok(this.commentService.getComments(recipeId, principal.getName()));
    }

    @PostMapping("/api/{recipeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(@PathVariable Long recipeId,
                                                       Principal principal,
                                                       @RequestBody @Valid CommentAddBindingModel commentAddBindingModel) {

        CommentAddServiceModel commentServiceModel = this.modelMapper.map(commentAddBindingModel, CommentAddServiceModel.class);
        commentServiceModel.setRecipeId(recipeId)
                .setAuthor(principal.getName());

        CommentViewModel newComment = this.commentService.addComment(commentServiceModel);

        URI locationOfNewComment = URI.create(String.format("/api/%s/comments/%s", recipeId, newComment.getId()));

        return ResponseEntity.created(locationOfNewComment)
                .body(newComment);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> onValidationFailure(MethodArgumentNotValidException ex) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);

        ex.getFieldErrors().forEach(fe -> apiError.addFieldWithError(fe.getField()));

        return ResponseEntity.badRequest().body(apiError);
    }
}
