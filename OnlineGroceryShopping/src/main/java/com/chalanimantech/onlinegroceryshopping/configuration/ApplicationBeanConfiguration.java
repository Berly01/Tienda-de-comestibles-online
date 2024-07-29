package com.chalanimantech.onlinegroceryshopping.configuration;

import org.modelmapper.ModelMapper;
import com.chalanimantech.onlinegroceryshopping.util.mappings.MappingsInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationBeanConfiguration {

    static ModelMapper modelMapper;

    static {
        modelMapper = new ModelMapper();
        MappingsInitializer.initMappings(modelMapper);
    }

    @Bean
    ModelMapper modelMapper() {
        return modelMapper;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
