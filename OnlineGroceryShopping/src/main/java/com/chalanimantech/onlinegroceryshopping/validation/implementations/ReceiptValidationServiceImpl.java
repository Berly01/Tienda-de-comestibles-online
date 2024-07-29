package com.chalanimantech.onlinegroceryshopping.validation.implementations;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Receipt;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.ReceiptServiceModel;
import com.chalanimantech.onlinegroceryshopping.validation.ReceiptValidationService;

import org.springframework.stereotype.Component;

@Component
public class ReceiptValidationServiceImpl implements ReceiptValidationService {
    @Override
    public boolean isValid(Receipt receipt) {
        return receipt != null;
    }

    @Override
    public boolean isValid(ReceiptServiceModel receiptServiceModel) {
        return receiptServiceModel != null;
    }
}
