package com.chalanimantech.onlinegroceryshopping.util.constants;

public final class ExceptionMessages {

    private ExceptionMessages() {}
    
    public static final String INCORRECT_USERNAME = "El nombre de usuario debe tener entre 3 y 20 símbolos.";
    public static final String INCORRECT_USERNAME_EMPTY_FIELD_ERROR_MSG = "No puede estar vacía, debe haber al menos 3 símbolos.";
    public static final String INCORRECT_PASSWORD = "No puede estar vacía, debe haber al menos 3 símbolos.";
    public static final String INCORRECT_ADDRESS = "No puede estar vacía, debe haber al menos 5 símbolos.";
    public static final String INCORRECT_EMAIL = "El correo electrónico debe ser válido y cumplir con RFC822.";
    public static final String INVALID_PRODUCT_EX_MSG = "Producto no válido.";
    public static final String USER_NOT_FOUND_EX_MSG = "Usuario no encontrado.";
    public static final String CATEGORY_NOT_FOUND_EX_MSG = "Categoría no encontrada.";
    public static final String ORDER_NOT_FOUND_EX_MSG = "Orden no encontrada.";
    public static final String PRODUCT_NOT_FOUND_EX_MSG = "Producto no encontrado.";
    public static final String PRODUCT_NAME_EXIST_EX_MSG = "El nombre del producto existe.";
    public static final String PRODUCT_ID_DOESNT_EXIST_EX_MSG = "¡No se encontró el producto con la identificación proporcionada!";
    public static final String RECEIPT_NAME_EXIST_EX_MSG = "El nombre del recibo existe.";
    public static final String THE_FIELDS_MUST_MATCH_EX_MSG = "Los campos deben coincidir.";
    public static final String PAGE_NOT_FOUND_EX_MSG = "Página no encontrada: ¡ERROR 404!\\nEsta página no existe...";

}
