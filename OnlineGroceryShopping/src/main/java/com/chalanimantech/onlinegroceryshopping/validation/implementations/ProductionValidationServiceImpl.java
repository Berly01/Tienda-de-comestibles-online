package com.chalanimantech.onlinegroceryshopping.validation.implementations;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Product;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.CategoryServiceModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.ProductServiceModel;
import com.chalanimantech.onlinegroceryshopping.validation.ProductValidationService;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductionValidationServiceImpl implements ProductValidationService {
    @Override
    public boolean isValid(Product product) {
        return product != null;
    }

    @Override
    public boolean isValid(ProductServiceModel product) {
        return product != null && areCategoriesValid(product.getCategories());
    }

    private boolean areCategoriesValid(List<CategoryServiceModel> categories) {
        return categories != null && !categories.isEmpty();
    }
}
