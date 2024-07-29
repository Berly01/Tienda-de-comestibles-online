package com.chalanimantech.onlinegroceryshopping.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.PRODUCT_NOT_FOUND_EX_MSG;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = PRODUCT_NOT_FOUND_EX_MSG)
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5321830785578861444L;
	private final int statusCode;

    public ProductNotFoundException() {
        this.statusCode = 404;
    }

    public ProductNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
