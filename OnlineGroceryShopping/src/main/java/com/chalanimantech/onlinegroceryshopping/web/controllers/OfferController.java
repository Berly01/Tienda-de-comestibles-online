package com.chalanimantech.onlinegroceryshopping.web.controllers;

import org.modelmapper.ModelMapper;

import com.chalanimantech.onlinegroceryshopping.domain.models.service.OfferServiceModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.view.OfferViewModel;
import com.chalanimantech.onlinegroceryshopping.service.OfferService;
import com.chalanimantech.onlinegroceryshopping.web.annotations.PageTitle;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.*;

@RestController
public class OfferController extends BaseController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/sales")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(SALES)
    public ModelAndView topOffers(ModelAndView modelAndView) {
        return view("offer/sales", modelAndView);
    }

    @GetMapping("/fetch/sales/{category}")
    public List<OfferViewModel> fetchByCategory(@PathVariable String category) {
        return mapOfferServiceToViewModel(this.offerService.findAllOffers());
    }
    
    @GetMapping("offers/fetch")
    public List<OfferViewModel> fetchAllOffers() {
        return mapOfferServiceToViewModel(this.offerService.findAllOffers());
    }
    
    private List<OfferViewModel> mapOfferServiceToViewModel(List<OfferServiceModel> offerServiceModel){ 	
    	return offerServiceModel.stream()
                .map(product -> modelMapper.map(product, OfferViewModel.class))
                .collect(Collectors.toList());
    } 
}
