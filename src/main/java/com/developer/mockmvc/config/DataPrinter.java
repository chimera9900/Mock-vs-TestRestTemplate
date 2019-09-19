package com.developer.mockmvc.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataPrinter {
	@Autowired
	TimeFactory timeFactory;
	
	public String printDate() {
		return "Now it is " + timeFactory.now();
	}
	
	@PostConstruct
	public void print() {
		System.out.println("Output:\t" + printDate());
	}
}
