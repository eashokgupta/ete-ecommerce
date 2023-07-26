package com.etelligens.ecommerce.utils;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
public class StringToObjectConvert {
	
	private final ObjectMapper objectMapper;

	public StringToObjectConvert() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T convertJsonToObject(String jsonString, Class<T> objectType) {
        try {
    		objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(jsonString, objectType);
        } catch (Exception e) {
           
            e.printStackTrace();
            return null; 
        }
    }

}
