package com.chalanimantech.onlinegroceryshopping.service;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    CategoryServiceModel addCategory(CategoryServiceModel categoryServiceModel);

    List<CategoryServiceModel> findAllCategories();

    CategoryServiceModel findCategoryById(String id);

    CategoryServiceModel editCategory(String id, CategoryServiceModel categoryServiceModel);

    CategoryServiceModel deleteCategory(String id);

    List<CategoryServiceModel> findAllFilteredCategories();
}
