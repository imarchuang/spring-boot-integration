package com.imarchuang.spring.integration.intertech.lab10.aggregator;

import org.apache.commons.csv.CSVRecord;
import org.springframework.integration.annotation.Aggregator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mhuang on 9/19/2016.
 */
@Component
public class CsvRecordsAggregator {
    @Aggregator(inputChannel = "csvObjectProcessedChannel", outputChannel = "aggregatedCsvObjectOutputChannel")
    public List<CSVRecord> aggregate(Collection<Message<?>> records) {
        List<CSVRecord> csvRecords = new ArrayList<CSVRecord>();
        for (Message<?> msg : records) {
            csvRecords.add((CSVRecord) msg.getPayload());
            //csvRecords.((int) msg.getHeaders().get(OrderComponentsCorrelationStrategy.CORRELATION_KEY));
        }
        return csvRecords;
    }
}
