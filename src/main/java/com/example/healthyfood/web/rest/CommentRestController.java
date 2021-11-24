package com.example.healthyfood.web.rest;

import com.example.healthyfood.model.binding.CommentBindingModel;
import com.example.healthyfood.model.service.CommentServiceModel;
import com.example.healthyfood.model.validator.ApiError;
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
@RequestMapping("/api")
public class CommentRestController {

    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentRestController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{recipeId}/comments")
    public ResponseEntity<List<CommentViewModel>> getComments(@PathVariable Long recipeId,
                                                              Principal principal) {

        return ResponseEntity.ok(this.commentService.getComments(recipeId, principal.getName()));
    }

    @PostMapping("/{recipeId}/comments")
    public ResponseEntity<CommentViewModel> newComment(@PathVariable Long recipeId,
                                                       Principal principal,
                                                       @RequestBody @Valid CommentBindingModel commentBindingModel) {

        CommentServiceModel commentServiceModel = this.modelMapper.map(commentBindingModel, CommentServiceModel.class);
        commentServiceModel.setRecipeId(recipeId);

        CommentViewModel newComment = this.commentService.addComment(commentServiceModel);

        URI locationOfNewComment = URI.create(String.format("/api/%d/comments/%s", recipeId, newComment.getId()));

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
