package com.parkhomenko.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class CsvServiceV2 {

    private final ObjectReader objectReader;

    public CsvServiceV2() {
        objectReader = objectReader();
    }

    List<Pojo> getObjectsFromCsvFile() throws IOException {
        final Path path = new ClassPathResource("convertcsv.csv").getFile().toPath();

        MappingIterator<Pojo> dtoMappingIterator = objectReader.readValues(
                Files.readAllBytes(path));

        return dtoMappingIterator.readAll();
    }

    private ObjectReader objectReader() {
        final CsvMapper mapper = new CsvMapper();

        final CsvSchema schema = mapper.schemaWithHeader()
                .withColumnSeparator(',');

        return mapper.reader(schema)
                .forType(Pojo.class);
    }
}
