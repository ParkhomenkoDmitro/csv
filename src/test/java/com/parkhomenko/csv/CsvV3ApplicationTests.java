package com.parkhomenko.csv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvV3ApplicationTests {

	@Autowired
	private CsvServiceV3 csvService;

	@Test
	void createSchemaBasedOnJavaClass() throws Exception {
		final String s = csvService.readAllExample();
		System.out.println(s);
		Assertions.assertNotNull(s);
	}

}
