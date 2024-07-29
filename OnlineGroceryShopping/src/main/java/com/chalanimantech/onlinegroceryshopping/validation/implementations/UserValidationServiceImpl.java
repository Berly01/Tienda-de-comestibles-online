package com.chalanimantech.onlinegroceryshopping.validation.implementations;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.UserServiceModel;
import com.chalanimantech.onlinegroceryshopping.validation.UserValidationService;

import org.springframework.stereotype.Component;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
