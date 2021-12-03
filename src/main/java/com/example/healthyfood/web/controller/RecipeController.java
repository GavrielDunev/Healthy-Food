package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.RecipeEditBindingModel;
import com.example.healthyfood.model.service.RecipeEditServiceModel;
import com.example.healthyfood.model.view.RecipeDetailsViewModel;
import com.example.healthyfood.model.view.RecipeEditViewModel;
import com.example.healthyfood.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    private final RecipeService recipeService;
    private final ModelMapper modelMapper;

    public RecipeController(RecipeService recipeService, ModelMapper modelMapper) {
        this.recipeService = recipeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/details/{id}")
    public String recipeDetails(@PathVariable Long id, Model model,
                                Principal principal) {

        RecipeDetailsViewModel recipeDetailsView = this.recipeService.getRecipeDetailsViewById(id, principal.getName());

        model.addAttribute("recipe", recipeDetailsView);

        return "details";
    }

    @PreAuthorize("@recipeServiceImpl.isOwner(#id, #principal.name)")
    @GetMapping("/edit/{id}")
    public String editRecipe(@PathVariable Long id, Model model,
                             Principal principal) {

        RecipeEditViewModel recipeEditView = this.recipeService.getRecipeEditViewById(id);
        RecipeEditBindingModel recipeEditBindingModel = this.modelMapper.map(recipeEditView, RecipeEditBindingModel.class)
                .setIsMeal(recipeEditView.getIsMeal());

        model.addAttribute("recipeEditBindingModel", recipeEditBindingModel);

        return "edit-recipe";
    }

    @GetMapping("/edit/errors/{id}")
    public String editRecipeErrors(@PathVariable Long id) {

        return "edit-recipe";
    }

    @PreAuthorize("@recipeServiceImpl.isOwner(#id, #principal.name)")
    @PatchMapping("/edit/{id}")
    public String editRecipeConfirm(@PathVariable Long id,
                                    @Valid RecipeEditBindingModel recipeEditBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) {

        if (bindingResult.hasErrors() || (recipeEditBindingModel.getCategory() == null && recipeEditBindingModel.isMeal())) {
            redirectAttributes.addFlashAttribute("recipeEditBindingModel", recipeEditBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeEditBindingModel", bindingResult);

            if (recipeEditBindingModel.getCategory() == null && recipeEditBindingModel.isMeal()) {
                redirectAttributes.addFlashAttribute("invalidCategory", true);
            }

            return "redirect:/recipes/edit/errors/" + id;
        }

        RecipeEditServiceModel recipeEditServiceModel = this.modelMapper.map(recipeEditBindingModel, RecipeEditServiceModel.class);

        this.recipeService.editRecipe(recipeEditServiceModel);

        return "redirect:/recipes/details/" + id;
    }

    @PreAuthorize("@recipeServiceImpl.isOwner(#id, #principal.name)")
    @DeleteMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable Long id,
                               Principal principal) {

        this.recipeService.deleteRecipe(id);

        return "redirect:/";
    }

}