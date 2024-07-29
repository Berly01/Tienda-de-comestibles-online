package com.chalanimantech.onlinegroceryshopping.validation;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Product;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.ProductServiceModel;

public interface ProductValidationService {
    boolean isValid(Product product);
    boolean isValid(ProductServiceModel product);
}
