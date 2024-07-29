package com.chalanimantech.onlinegroceryshopping.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.CATEGORY_NOT_FOUND_EX_MSG;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = CATEGORY_NOT_FOUND_EX_MSG)
public class CategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6475154373557240346L;
	private final int statusCode;

    public CategoryNotFoundException() {
        this.statusCode = 404;
    }

    public CategoryNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
