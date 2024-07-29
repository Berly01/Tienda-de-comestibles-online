package com.chalanimantech.onlinegroceryshopping.web.controllers;

import org.modelmapper.ModelMapper;
import com.chalanimantech.onlinegroceryshopping.domain.models.binding.CategoryAddBindingModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.CategoryServiceModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.view.CategoryViewModel;
import com.chalanimantech.onlinegroceryshopping.util.error.CategoryNotFoundException;
import com.chalanimantech.onlinegroceryshopping.service.CategoryService;
import com.chalanimantech.onlinegroceryshopping.web.annotations.PageTitle;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.MODEL;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.CATEGORIES;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.CATEGORIES_TO_LOWER_CASE;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.ERROR;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.MESSAGE;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.STATUS_CODE;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PageTitle("Add Category")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategory(@ModelAttribute(name = MODEL) CategoryAddBindingModel categoryAddBindingModel,
                                    ModelAndView modelAndView) {

        return loadAndReturnModelAndView(categoryAddBindingModel, modelAndView);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView addCategoryConfirm(@Valid @ModelAttribute(name = MODEL) CategoryAddBindingModel model,
                                           BindingResult bindingResult, ModelAndView modelAndView) {

        CategoryServiceModel categoryServiceModel = modelMapper.map(model, CategoryServiceModel.class);

        if (bindingResult.hasErrors() 
        		|| categoryService.addCategory(categoryServiceModel) == null) {

            return loadAndReturnModelAndView(model, modelAndView);
        }
        
        return redirect("/categories/all");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle(CATEGORIES)
    public ModelAndView allCategories(ModelAndView modelAndView) {

        List<CategoryViewModel> categories =
                mapCategoryServiceToViewModel(categoryService.findAllFilteredCategories());

        modelAndView.addObject(CATEGORIES_TO_LOWER_CASE, categories);

        return view("category/all-categories", modelAndView);
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Edit Category")
    public ModelAndView editCategory(@PathVariable String id, ModelAndView modelAndView) {

        CategoryViewModel categoryViewModel =
                modelMapper.map(categoryService.findCategoryById(id), CategoryViewModel.class);

        modelAndView.addObject(MODEL, categoryViewModel);

        return view("category/edit-category", modelAndView);
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView editCategoryConfirm(@PathVariable String id, @Valid @ModelAttribute CategoryAddBindingModel model,
                                            BindingResult bindingResult, ModelAndView modelAndView) {

        CategoryServiceModel categoryServiceModel =
                modelMapper.map(model, CategoryServiceModel.class);

        if (bindingResult.hasErrors() 
        		|| categoryService.editCategory(id, categoryServiceModel) == null) {

            modelAndView.addObject(MODEL, model);

            return view("category/edit-category", modelAndView);
        }

        return redirect("/categories/all");
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    @PageTitle("Delete Category")
    public ModelAndView deleteCategory(@PathVariable String id, ModelAndView modelAndView) {

        CategoryViewModel categoryViewModel = modelMapper.map(categoryService.findCategoryById(id), CategoryViewModel.class);
        modelAndView.addObject(MODEL, categoryViewModel);

        return view("category/delete-category", modelAndView);
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ModelAndView deleteCategoryConfirm(@PathVariable String id) {

        categoryService.deleteCategory(id);

        return redirect("/categories/all");
    }

    @GetMapping("/fetch")
    /*@PreAuthorize("hasRole('ROLE_MODERATOR')")*/
    public List<CategoryViewModel> fetchCategories() {
        return mapCategoryServiceToViewModel(categoryService.findAllFilteredCategories());
    }

    private List<CategoryViewModel> mapCategoryServiceToViewModel
            (List<CategoryServiceModel> categoryServiceModels){
        return categoryServiceModels.stream()
                .map(product -> modelMapper.map(product, CategoryViewModel.class))
                .collect(Collectors.toList());
    }

    private ModelAndView loadAndReturnModelAndView(CategoryAddBindingModel categoryAddBindingModel, ModelAndView modelAndView) {

        modelAndView.addObject(MODEL, categoryAddBindingModel);

        return view("category/add-category", modelAndView);
    }

    @ExceptionHandler({CategoryNotFoundException.class})
    public ModelAndView handleProductNotFound(CategoryNotFoundException e) {
       
    	ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(MESSAGE, e.getMessage());
        modelAndView.addObject(STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }
}


