package com.developer.mockmvc.controller;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@RestController
@RequestMapping
public class DemoController {
	
	@GetMapping
	public LocalDateTime sayTheTime() {
		return LocalDateTime.now();
	}
	
	@GetMapping(value = "/many")
	public String sayTheTimeMany(@RequestParam() String name, 
			@RequestParam(defaultValue = "10") int repetitions) {
		return IntStream.rangeClosed(1, repetitions)
				.mapToObj(i -> i + ":\t" + name)
				.collect(Collectors.joining("; \n"));
		
	}
	
	
	@GetMapping(value = "/manyParams")
	public String sayTheTimeManyParams(Params params)  {
		return IntStream.rangeClosed(1, params.getRepetitions())
				.mapToObj(i -> i + ":\t" + params.getName())
				.collect(Collectors.joining("; \n"));
		
	}
	
	@GetMapping(value = "/many/{name}/{repetitions}")
	public String sayTheTimeManyParamsPath(Params params)  {
		return IntStream.rangeClosed(1, params.getRepetitions())
		.mapToObj(i -> i + ":\t" + params.getName())
		.collect(Collectors.joining("; \n"));
		
	}
	
	@GetMapping(value = "/many2/{name}/{repetitions}")
	public String sayTheTimeManyPathExplicit(@PathVariable String name, 
			@PathVariable int repetitions) {
		return IntStream.rangeClosed(1, repetitions)
				.mapToObj(i -> i + ":\t" + name)
				.collect(Collectors.joining("; \n"));
		
	}
	
	@RequestMapping(value = "/manyParams", method = RequestMethod.POST)
	public String sayTheTimeManyParamsPost(@RequestBody Params params)  {
		return IntStream.rangeClosed(1, params.getRepetitions())
				.mapToObj(i -> i + ":\t" + params.getName())
				.collect(Collectors.joining("; \n"));
		
	}
	
	@Data@NoArgsConstructor
	@AllArgsConstructor
	public static class Params {
		String name = "John";
		int repetitions = 5;

	}
	
}


