package com.developer.mockmvc;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.developer.mockmvc.controller.DemoController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
	@Autowired
	MockMvc mvc;
	
	@Test
	public void sayTheTime() throws UnsupportedEncodingException, Exception {
		String result1 = mvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().is2xxSuccessful())
				.andReturn().getResponse().getContentAsString();
		String result2 = mvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		
		assertTrue(result1.contains("2019-09-18T"));
		assertNotEquals(result1, result2);
	}
	
	@Test
	public void sayTheTimeMany() throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(MockMvcRequestBuilders.get("/many")
				.param("name", "Jimmy")
				.param("repetitions", "5"))
		.andExpect(status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		assertTrue(result.contains("Jimmy"));
	}
	
	@Test
	public void sayTheTimeManyParams() throws UnsupportedEncodingException, Exception  {
		String result = mvc.perform(MockMvcRequestBuilders.get("/manyParams")
				.param("name", "Jimmy")
				.param("repetitions", "5"))
		.andExpect(status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		assertTrue(result.contains("Jimmy"));
	}
	
	@Test
	public void sayTheTimeManyParamsPath() throws UnsupportedEncodingException, Exception  {
		String result = mvc.perform(MockMvcRequestBuilders.get("/many/{name}/{repetitions}",
				"Jimmy","5")
				)
		.andExpect(status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		assertTrue(result.contains("Jimmy"));
		
	}
	
	@RequestMapping(value = "/manyParams", method = RequestMethod.POST)
	@Test
	public void sayTheTimeManyParamsPost() throws UnsupportedEncodingException, JsonProcessingException, Exception  {
		String result = mvc.perform(MockMvcRequestBuilders.post("/manyParams")
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(new ObjectMapper().writeValueAsString(new DemoController.Params())))
		.andExpect(status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		
		assertTrue(result.contains("John"));
		assertTrue(result.contains("5:"));
		assertTrue(!result.contains("6:"));
		
		
	}

}
