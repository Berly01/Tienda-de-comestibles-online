package com.chalanimantech.onlinegroceryshopping.web.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.FAVICON;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.FAVICON_URL;

@Component
public class FaviconInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
            modelAndView.addObject(FAVICON, FAVICON_URL);
        }
    }
}
