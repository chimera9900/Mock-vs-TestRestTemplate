package com.developer.mockmvc;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.developer.mockmvc.controller.DemoController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoControllerRestTemplateTest {
	
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void sayTheTime() throws UnsupportedEncodingException, Exception {
		String result1 = restTemplate.getForObject("/", String.class);
		String result2 = restTemplate.getForObject("/", String.class);
			
		assertTrue(result1.contains("2019-09-18T"));
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void sayTheTimeMany() throws UnsupportedEncodingException, Exception {
		String result = restTemplate.getForObject("/many?name=Jimmy&repetitions=5", String.class);
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		assertTrue(result.contains("Jimmy"));
	}
	
	@Test
	public void sayTheTimeManyParams() throws UnsupportedEncodingException, Exception  {
		String result = restTemplate.getForObject("/manyParams?name=Jimmy&repetitions=5", String.class);
		
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		assertTrue(result.contains("Jimmy"));
	}
	
	@Test
	public void sayTheTimeManyParamsPath() throws UnsupportedEncodingException, Exception  {
		String result = restTemplate.getForEntity("/many/{name}/{repetitions}", String.class, "Jimmy", "5").getBody();
		
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		assertTrue(result.contains("Jimmy"));
		
	}
	
	@Test
	public void sayTheTimeManyParamsPost() throws UnsupportedEncodingException, JsonProcessingException, Exception  {
		String result = restTemplate.postForObject("/manyParams", 
				new DemoController.Params(),
				String.class);
		
		assertTrue(result.contains("John"));
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		
		
	}

}
