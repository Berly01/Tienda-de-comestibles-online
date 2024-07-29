package com.chalanimantech.onlinegroceryshopping.util.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.RECEIPT_NAME_EXIST_EX_MSG;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = RECEIPT_NAME_EXIST_EX_MSG)
public class ReceiptNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -640107890868494411L;
	private final int statusCode;

    public ReceiptNotFoundException() {
        this.statusCode = 404;
    }

    public ReceiptNotFoundException(String message) {
        super(message);
        this.statusCode = 404;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
