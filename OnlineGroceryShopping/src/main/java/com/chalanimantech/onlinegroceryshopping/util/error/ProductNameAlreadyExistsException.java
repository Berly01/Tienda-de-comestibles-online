package com.chalanimantech.onlinegroceryshopping.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.PRODUCT_NAME_EXIST_EX_MSG;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = PRODUCT_NAME_EXIST_EX_MSG)
public class ProductNameAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -5930727056471868272L;
	private final int statusCode;

    public ProductNameAlreadyExistsException() {
        this.statusCode = 409;
    }

    public ProductNameAlreadyExistsException(String message) {
        super(message);
        this.statusCode = 409;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
