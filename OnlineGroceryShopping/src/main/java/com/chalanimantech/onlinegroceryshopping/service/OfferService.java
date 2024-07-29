package com.chalanimantech.onlinegroceryshopping.service;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {
    List<OfferServiceModel> findAllOffers();
}
