package com.developer.mockmvc.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	
	@Bean
	public TimeFactory timeFactory() {
		return new TimeFactory();
	}
	
	@PostConstruct
	public void post() {
		System.out.println("Instance:\t" + timeFactory());
		System.out.println("Instance:\t" + timeFactory());
	}
}
