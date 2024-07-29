package com.chalanimantech.onlinegroceryshopping.configuration;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.API_KEY;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.CLOUD_NAME;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.API_SECRET;

@Configuration
public class ApplicationCloudConfiguration {

    @Value("${cloudinary.cloud-name}")
    private String cloudApiName;
    
    @Value("${cloudinary.api-key}")
    private String cloudApiKey;
    
    @Value("${cloudinary.api-secret}")
    private String cloudApiSecret;

    @Bean
    Cloudinary cloudinary() {  	 	
    	var hashMap = new HashMap<String, Object>();  	
    	hashMap.put(CLOUD_NAME, cloudApiName);
    	hashMap.put(API_KEY, cloudApiKey);
    	hashMap.put(API_SECRET, cloudApiSecret); 
    	
        return new Cloudinary(hashMap);
    }
}