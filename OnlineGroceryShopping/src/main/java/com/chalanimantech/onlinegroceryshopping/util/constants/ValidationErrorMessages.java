package com.chalanimantech.onlinegroceryshopping.util.constants;

public final class ValidationErrorMessages {

    private ValidationErrorMessages() {}

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static final String ROOT_ADMIN = "ROOT_ADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String DEFAULT_USER_NOT_FOUND_EX_MSG = "Username not found.";
    public static final String DEFAULT_NOT_AUTHORIZE_EX_MSG = "You are not authorize to update ROOT_ADMIN role.";

    public static final String PASSWORDS_DOES_NOT_MATCH_ERROR_MSG = "Passwords doesnt match.";

    public static final String CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG = "Category name can't be empty.";
    public static final String CATEGORY_NAME_MAX_LENGTH_ERROR_MSG = "Category name should be max 20 characters long.";

    public static final String PRODUCT_NAME_EMPTY_FIELD_ERROR_MSG = "Product name can't be empty.";
    public static final String PRODUCT_NAME_LENGTH = "Product name should be between 3 and 20 symbols!";
    public static final String PRODUCT_DESCRIPTION_MAX_LENGTH_ERROR_MSG = "Product description should be max 50 characters long.";
    public static final String PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG = "Product description can't be empty.";
    public static final String PRODUCT_PRICE_EMPTY_FIELD_ERROR_MSG = "Product price can't be empty. Should be at least 0.";
    public static final String PRODUCT_IMAGE_EMPTY_FIELD_ERROR_MSG = "Upload product image.";
    public static final String PRODUCT_CATEGORIES_EMPTY_FIELD_ERROR_MSG = "Select at least one category.";

    public static final String VALID_EMAIL_ADDRESS_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
}
