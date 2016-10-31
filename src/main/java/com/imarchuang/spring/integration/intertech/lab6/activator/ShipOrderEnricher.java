package com.imarchuang.spring.integration.intertech.lab6.activator;

import com.imarchuang.spring.integration.intertech.domain.Shiporder;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * Created by mhuang on 9/9/2016.
 */
@Service
public class ShipOrderEnricher {

    @ServiceActivator(inputChannel="total-price-enricher-channel")
    public double computeTotal(Message<Shiporder> order) {
        double sum = 0;
        for (Shiporder.Item item : order.getPayload().getItem()) {
            sum = sum
                    + (item.getPrice().doubleValue() * item.getQuantity()
                    .intValue());
        }
        return sum;
    }
}
