package com.imarchuang.spring.integration.intertech.lab7.activator;

import com.imarchuang.spring.integration.intertech.domain.Shiporder;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * Created by mhuang on 9/9/2016.
 */
@Service("revenueService")
public class RevenueServiceActivator {

    private double revenue = 0.0;

    @ServiceActivator(inputChannel="revenueProcessingChannel", outputChannel="outboundChannel")
    public Message<Shiporder> adjustRevenue(Message<Shiporder> order) {
        System.out.println("Processing order");
        for (Shiporder.Item item : order.getPayload().getItem()) {
            revenue = revenue
                    + (item.getPrice().doubleValue() * item.getQuantity()
                    .intValue());
            System.out.println("Revenue now up to:  " + revenue);
        }
        System.out.println("Done processing order");
        return order;
    }
}
