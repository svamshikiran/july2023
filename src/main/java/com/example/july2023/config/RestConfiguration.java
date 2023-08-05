package com.example.july2023.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //Config class which will be executed during server startup
public class RestConfiguration {
	
	@Bean //Method level bean creation
	public RestTemplate getRestTemplate()
	{
		RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate template = builder.build();

		return template;
	}

}
