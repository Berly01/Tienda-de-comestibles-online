package com.chalanimantech.onlinegroceryshopping.domain.models.service;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.CATEGORY_NAME_MAX_LENGTH;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ValidationErrorMessages.CATEGORY_NAME_MAX_LENGTH_ERROR_MSG;

public class CategoryServiceModel extends BaseServiceModel {

    private String name;
    private boolean isDeleted;

    public CategoryServiceModel() {
    	super();
    }

    @NotNull(message = CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG)
    @NotEmpty(message = CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG)
    @Length(max = CATEGORY_NAME_MAX_LENGTH, message = CATEGORY_NAME_MAX_LENGTH_ERROR_MSG)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
