package com.parkhomenko.csv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
class CsvV2ApplicationTests {

	@Autowired
	private CsvServiceV2 csvService;

	@Test
	void createSchemaBasedOnJavaClass() throws IOException {
		var schemaBasedOnJavaClass = csvService.getObjectsFromCsvFile();

		Assertions.assertEquals(100, schemaBasedOnJavaClass.size());

		final var firstItem = schemaBasedOnJavaClass.get(0);

		Assertions.assertEquals(51, firstItem.age);
		Assertions.assertEquals("(256) 953-5773", firstItem.phone);
		Assertions.assertEquals("Lester Taylor", firstItem.name);
		Assertions.assertEquals(LocalDate.of(1996, 4, 16), firstItem.date);
	}

}
