package com.chalanimantech.onlinegroceryshopping.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Order;
import com.chalanimantech.onlinegroceryshopping.domain.entities.Receipt;
import com.chalanimantech.onlinegroceryshopping.domain.entities.User;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.ReceiptServiceModel;
import com.chalanimantech.onlinegroceryshopping.util.error.OrderNotFoundException;
import com.chalanimantech.onlinegroceryshopping.util.error.ReceiptNotFoundException;
import com.chalanimantech.onlinegroceryshopping.repository.OrderRepository;
import com.chalanimantech.onlinegroceryshopping.repository.ReceiptRepository;
import com.chalanimantech.onlinegroceryshopping.repository.UserRepository;
import com.chalanimantech.onlinegroceryshopping.validation.ReceiptValidationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.USER_NOT_FOUND_EX_MSG;

@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final ReceiptValidationService receiptValidationService;
    private final ModelMapper modelMapper;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository, OrderRepository orderRepository,
                              OrderService orderService, UserRepository userRepository,
                              ReceiptValidationService receiptValidationService, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.receiptValidationService = receiptValidationService;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ReceiptServiceModel> findAllReceiptsByUsername(String username) {
        return this.receiptRepository
                .findAllReceiptsByRecipient_UsernameOrderByIssuedOn(username)
                .stream()
                .map(r -> this.modelMapper.map(r, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptServiceModel> findAllReceipts() {
        return this.receiptRepository
                .findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }
    @Override
    public void receiptRegister(ReceiptServiceModel receiptServiceModel) {
        if (!receiptValidationService.isValid(receiptServiceModel)){
            throw new IllegalArgumentException();
        }
        Receipt receipt = this.modelMapper.map(receiptServiceModel, Receipt.class);
        this.receiptRepository.save(receipt);
    }

    @Override
    public ReceiptServiceModel getReceiptById(String id) {
        Receipt receipt = this.receiptRepository.findById(id)
                .orElseThrow(ReceiptNotFoundException::new);
        return modelMapper.map(receipt, ReceiptServiceModel.class);
    }

    @Override
    public void createReceipt(String orderId, String name) {
        Order order = this.orderRepository.findById(orderId)
                .orElseThrow(OrderNotFoundException::new);
        User recipient = this.userRepository.findByUsername(name)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_EX_MSG));

        Receipt receipt = new Receipt();

        receipt.setFee(order.getTotalPrice());
        receipt.setIssuedOn(LocalDateTime.now());
        receipt.setOrder(order);
        receipt.setRecipient(recipient);

        this.receiptRepository.save(receipt);

        this.orderService.changeOrderStatus(orderId);
    }

    @Override
    public ReceiptServiceModel findReceiptById(String receiptId) {

        Receipt receipt = this.receiptRepository.findById(receiptId)
                .orElseThrow(ReceiptNotFoundException::new);

        return modelMapper.map(receipt, ReceiptServiceModel.class);
    }
}
