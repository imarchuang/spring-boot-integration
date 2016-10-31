package com.imarchuang.spring.integration.intertech.lab10.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Sample service for demo purpose only.
 *
 * Created by mhuang on 9/19/2016.
 */
@Component
public class PriceService {
    private static final double defaultPrice = 5.0d;
    private static final Map<String, Double> pricesByLetters = new HashMap<String, Double>();

    static {
        pricesByLetters.put("a", 11d);
        pricesByLetters.put("b", 3.6d);
        pricesByLetters.put("c", 2d);
        pricesByLetters.put("l", 2d);
        pricesByLetters.put("p", 10d);
    };

    public double priceFromProduct(CSVRecord record) {
        String idLetter = (""+record.get(0).charAt(0)).toLowerCase();
        if (pricesByLetters.containsKey(idLetter)) {
            return pricesByLetters.get(idLetter);
        }
        return defaultPrice;
    }

}
