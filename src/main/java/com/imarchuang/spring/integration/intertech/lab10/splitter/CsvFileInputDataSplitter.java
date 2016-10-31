package com.imarchuang.spring.integration.intertech.lab10.splitter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.integration.annotation.Splitter;
import org.springframework.stereotype.Component;
import sun.nio.cs.MS1250;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by mhuang on 9/18/2016.
 */
@Component
public class CsvFileInputDataSplitter {

    private static final String [] INPUT_FILE_HEADER_MAPPING = {"isbn","title","author","publisher","language"};

    @Splitter(inputChannel = "fileInputChannel", outputChannel = "csvObjectInputChannel")
    public List<CSVRecord> split(String input) {

        CSVFormat inputCsvFileFormat = CSVFormat.DEFAULT.withHeader(INPUT_FILE_HEADER_MAPPING);

        CSVParser csvFileParser = null;
        List<CSVRecord> csvRecords = null;
        try {
            //initialize CSVParser object
            csvFileParser = CSVParser.parse(new File(input), new MS1250(), inputCsvFileFormat);
            //Get a list of CSV file records
            csvRecords = csvFileParser.getRecords();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return csvRecords;
    }
}
