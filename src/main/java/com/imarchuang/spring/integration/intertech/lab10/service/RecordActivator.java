package com.imarchuang.spring.integration.intertech.lab10.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Created by mhuang on 9/19/2016.
 */
@Component
public class RecordActivator {

    @Autowired
    private PriceService priceService;

    public Message<CSVRecord> quoteForTheBestPrice(Message<CSVRecord> msg) {
        CSVRecord record = msg.getPayload();
        //msg.getHeaders().put("TEST", priceService.priceFromProduct(record));
        System.out.println(record.toString());
        return msg;
    }
}
