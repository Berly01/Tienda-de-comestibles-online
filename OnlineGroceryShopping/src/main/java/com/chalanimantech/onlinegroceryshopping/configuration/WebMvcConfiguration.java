package com.chalanimantech.onlinegroceryshopping.configuration;

import com.chalanimantech.onlinegroceryshopping.web.interceptors.FaviconInterceptor;
import com.chalanimantech.onlinegroceryshopping.web.interceptors.GreetingInterceptor;
import com.chalanimantech.onlinegroceryshopping.web.interceptors.TitleInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final TitleInterceptor titleInterceptor;
    private final FaviconInterceptor faviconInterceptor;
    private final GreetingInterceptor greetingInterceptor;

    public WebMvcConfiguration(TitleInterceptor titleInterceptor, FaviconInterceptor faviconInterceptor, GreetingInterceptor greetingInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.faviconInterceptor = faviconInterceptor;
        this.greetingInterceptor = greetingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.titleInterceptor);
        registry.addInterceptor(this.faviconInterceptor);
        registry.addInterceptor(this.greetingInterceptor);
    }
}
