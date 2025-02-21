package com.bytebase;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class ByteBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ByteBaseApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)
		.setPropertyCondition(Conditions.isNotNull());
		
		return modelMapper; 
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
}
