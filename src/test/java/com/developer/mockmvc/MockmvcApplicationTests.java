package com.developer.mockmvc;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.developer.mockmvc.config.DataPrinter;
import com.developer.mockmvc.config.TimeFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MockmvcApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	DataPrinter dataPrinter;
	@MockBean
	TimeFactory  timeFactory;
	
	@Test
	public void dataPrinterWorks() {
		when(timeFactory.now()).thenReturn(LocalDateTime.of(2019, 9, 18, 16, 29, 59));
		assertEquals("Now it is 2019-09-18T16:29:59", dataPrinter.printDate());
	}

}
