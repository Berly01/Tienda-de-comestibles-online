package com.chalanimantech.onlinegroceryshopping.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.ORDER_NOT_FOUND_EX_MSG;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = ORDER_NOT_FOUND_EX_MSG)
public class OrderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2892379016366845969L;
	private final int statusCode;

    public OrderNotFoundException() {
        this.statusCode = 404;
    }

    public OrderNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
