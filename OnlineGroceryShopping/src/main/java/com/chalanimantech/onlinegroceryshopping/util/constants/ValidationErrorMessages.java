package com.chalanimantech.onlinegroceryshopping.util.constants;

public final class ValidationErrorMessages {

    private ValidationErrorMessages() {}

    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";
    public static final String ROOT_ADMIN = "ROOT_ADMIN";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String DEFAULT_USER_NOT_FOUND_EX_MSG = "Usuario no encontrado.";
    public static final String DEFAULT_NOT_AUTHORIZE_EX_MSG = "No está autorizado a actualizar la función ROOT ADMIN.";

    public static final String PASSWORDS_DOES_NOT_MATCH_ERROR_MSG = "Las contraseñas no coinciden.";

    public static final String CATEGORY_NAME_EMPTY_FIELD_ERROR_MSG = "El nombre de la categoría no puede estar vacía.";
    public static final String CATEGORY_NAME_MAX_LENGTH_ERROR_MSG = "El nombre de la categoría debe tener un máximo de 20 caracteres.";

    public static final String PRODUCT_NAME_EMPTY_FIELD_ERROR_MSG = "El nombre del producto no puede estar vacío.";
    public static final String PRODUCT_NAME_LENGTH = "¡El nombre del producto debe tener entre 3 y 20 símbolos!";
    public static final String PRODUCT_DESCRIPTION_MAX_LENGTH_ERROR_MSG = "La descripción del producto debe tener un máximo de 50 caracteres.";
    public static final String PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG = "La descripción del producto no puede estar vacía.";
    public static final String PRODUCT_PRICE_EMPTY_FIELD_ERROR_MSG = "El precio del producto no puede estar vacío. Debe ser al menos 0.";
    public static final String PRODUCT_IMAGE_EMPTY_FIELD_ERROR_MSG = "Subir imagen del producto.";
    public static final String PRODUCT_CATEGORIES_EMPTY_FIELD_ERROR_MSG = "Seleccione al menos una categoría.";

    public static final String VALID_EMAIL_ADDRESS_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    public static final String PASSWORD_VALIDATION_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
}
