package com.chalanimantech.onlinegroceryshopping.service;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {

    List<ReceiptServiceModel> findAllReceiptsByUsername(String userId);

    List<ReceiptServiceModel> findAllReceipts();

    void receiptRegister(ReceiptServiceModel receiptServiceModel);

    ReceiptServiceModel getReceiptById(String id);

    void createReceipt(String orderId, String name);

    ReceiptServiceModel findReceiptById(String receiptId);
}
