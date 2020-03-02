package com.parkhomenko.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class CsvService {

    private CsvMapper mapper = new CsvMapper();

    /**
     * Create schema based on a Java class. Schema will have column names and ordering + column types.
     */
    List<Pojo> createSchemaBasedOnJavaClass() throws IOException {
        var resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("convertcsv.csv");
        var schema = mapper.schemaFor(Pojo.class).withSkipFirstDataRow(true); // schema from 'Pojo' definition

        MappingIterator<Pojo> it = mapper.readerFor(Pojo.class).with(schema).readValues(Objects.requireNonNull(resourceAsStream));

        return it.readAll();
    }

}
