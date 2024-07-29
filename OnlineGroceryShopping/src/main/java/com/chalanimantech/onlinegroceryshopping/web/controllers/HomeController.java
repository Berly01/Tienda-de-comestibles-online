package com.chalanimantech.onlinegroceryshopping.web.controllers;

import org.modelmapper.ModelMapper;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.CategoryServiceModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.view.CategoryViewModel;
import com.chalanimantech.onlinegroceryshopping.service.CategoryService;

import com.chalanimantech.onlinegroceryshopping.web.annotations.PageTitle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.*;

@RestController
public class HomeController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public HomeController(CategoryService categoryService, ModelMapper modelMapper) {

        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @PageTitle(INDEX)
    public ModelAndView renderIndexPage(Principal principal, ModelAndView modelAndView) {

        modelAndView.addObject(PRINCIPAL_TO_LOWER_CASE, principal);

        return view("/index", modelAndView);
    }

    @GetMapping("/home")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(HOME)
    public ModelAndView renderHomePage(Principal principal, ModelAndView modelAndView) {
        
        List<CategoryViewModel> categories = mapCategoryServiceToViewModel(categoryService.findAllFilteredCategories());
        
        modelAndView.addObject(PRINCIPAL_TO_LOWER_CASE, principal);
        modelAndView.addObject(CATEGORIES_TO_LOWER_CASE, categories);

        return view("/home", modelAndView);
    }

    private List<CategoryViewModel> mapCategoryServiceToViewModel(List<CategoryServiceModel> categoryServiceModels){
        return categoryServiceModels.stream()
                .map(product -> modelMapper.map(product, CategoryViewModel.class))
                .collect(Collectors.toList());
    }
}
