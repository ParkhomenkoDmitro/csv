package com.parkhomenko.csv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.baeldung.com/opencsv
 */
@Service
public class CsvServiceV3 {

    public List<String[]> readAll(Reader reader) throws Exception {
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> list = csvReader.readAll();
        reader.close();
        csvReader.close();
        return list;
    }

    public String readAllExample() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("test.csv").toURI()));

        final List<String[]> strings = readAll(reader);

        StringBuilder result = new StringBuilder();

        for (String[] string : strings) {
            result
                    .append(Arrays.toString(string))
                    .append("\n");
        }

        return result.toString();
    }

    public List<String[]> oneByOne(Reader reader) throws Exception {
        List<String[]> list = new ArrayList<>();
        CSVReader csvReader = new CSVReader(reader);
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            list.add(line);
        }
        reader.close();
        csvReader.close();
        return list;
    }

    public String oneByOneExample() throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("test.csv").toURI()));

        return oneByOne(reader).toString();
    }


    public List<String[]> readAllWithBuilder(Reader reader) throws Exception {
        CSVParser parser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(0)
                .withCSVParser(parser)
                .build();

        List<String[]> list = csvReader.readAll();

        reader.close();
        csvReader.close();
        return list;
    }


}
