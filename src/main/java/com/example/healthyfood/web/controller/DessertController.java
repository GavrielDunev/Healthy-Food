package com.example.healthyfood.web.controller;

import com.example.healthyfood.model.binding.RecipeAddBindingModel;
import com.example.healthyfood.model.service.RecipeAddServiceModel;
import com.example.healthyfood.model.view.RecipeSummaryViewModel;
import com.example.healthyfood.service.DessertService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/desserts")
public class DessertController {

    private final DessertService dessertService;
    private final ModelMapper modelMapper;

    public DessertController(DessertService dessertService, ModelMapper modelMapper) {
        this.dessertService = dessertService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String desserts(Model model) {

        List<RecipeSummaryViewModel> desserts = this.dessertService.getAllDessertViews();

        model.addAttribute("type", "Dessert");
        model.addAttribute("recipes", desserts);

        return "recipes";
    }

    @GetMapping("/add")
    public String dessertAdd(Model model) {

        model.addAttribute("type", "dessert");

        return "add-recipe";
    }

    @PostMapping("/add")
    public String dessertAddConfirm(@Valid RecipeAddBindingModel recipeAddBindingModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeAddBindingModel", recipeAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.recipeAddBindingModel", bindingResult);

            return "redirect:/desserts/add";
        }

        RecipeAddServiceModel recipeAddServiceModel = this.modelMapper.map(recipeAddBindingModel, RecipeAddServiceModel.class);

        this.dessertService.saveDessert(recipeAddServiceModel, principal.getName());

        return "redirect:/desserts";
    }

    @ModelAttribute
    public RecipeAddBindingModel recipeAddBindingModel() {
        return new RecipeAddBindingModel();
    }
}
