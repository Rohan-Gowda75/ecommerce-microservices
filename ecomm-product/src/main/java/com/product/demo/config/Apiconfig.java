package com.product.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Apiconfig {
	
	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

}
