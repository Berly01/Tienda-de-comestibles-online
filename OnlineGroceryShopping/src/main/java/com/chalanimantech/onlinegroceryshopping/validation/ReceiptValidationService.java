package com.chalanimantech.onlinegroceryshopping.validation;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Receipt;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.ReceiptServiceModel;

public interface ReceiptValidationService {
    boolean isValid(Receipt receipt);
    boolean isValid(ReceiptServiceModel receiptServiceModel);
}
