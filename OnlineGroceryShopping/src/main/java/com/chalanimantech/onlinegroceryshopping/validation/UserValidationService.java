package com.chalanimantech.onlinegroceryshopping.validation;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
