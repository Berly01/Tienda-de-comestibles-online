package com.chalanimantech.onlinegroceryshopping.web.interceptors;

import com.chalanimantech.onlinegroceryshopping.web.annotations.PageTitle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.TITLE;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.TITLE_GROCERY_STORE;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.DASH;

@Component
public class TitleInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        } else {
            if (handler instanceof HandlerMethod) {
                PageTitle methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PageTitle.class);

                if (methodAnnotation != null) {
                    modelAndView
                            .addObject(TITLE, TITLE_GROCERY_STORE + DASH + methodAnnotation.value());
                }
            }
        }
    }
}
