package com.chalanimantech.onlinegroceryshopping.web.controllers;

import org.springframework.web.servlet.ModelAndView;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.REDIRECT_BASE_CONTROLLER;

public abstract class BaseController {
	
    protected ModelAndView view(String view, ModelAndView modelAndView) {
       
    	modelAndView.setViewName(view);
        return modelAndView;
    }

    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView redirect(String url){
        return this.view(REDIRECT_BASE_CONTROLLER + url);
    }
}
